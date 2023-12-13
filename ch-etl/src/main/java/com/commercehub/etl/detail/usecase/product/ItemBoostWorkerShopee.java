package com.commercehub.etl.detail.usecase.product;

import com.commercehub.etl.core.entity.boost.Boost;
import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.BoostRepository;
import com.commercehub.etl.core.usecase.product.ItemBoostWorker;
import com.commercehub.rest.shopee.ShopeeProductService;
import com.commercehub.rest.shopee.input.BoostItemInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.BoostItemOutput;
import com.commercehub.rest.shopee.output.GetBoostedListOutput;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Dependent
public class ItemBoostWorkerShopee implements ItemBoostWorker {

    @Inject
    BoostRepository boostRepository;

    @Inject
    @RestClient
    ShopeeProductService productService;

    @Inject
    Logger log;

    @Override
    public Uni<Boolean> boost(Linking linking) {
        return Multi.createFrom()
                .item(() -> boostRepository.getFirst(linking.platform, linking.shopId))
                .flatMap(boosts -> Multi.createFrom().iterable(boosts))
                .flatMap(boost ->
                        getCurrentBoosting(linking)
                                .map(boosting -> Tuple.of(boost, boosting)))
                .map(boostBoostingTuple -> getItemToBoost(boostBoostingTuple.x(), boostBoostingTuple.y()))
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> boostItem(linking, ids))
                .map(output -> recordSuccessfulBoost(linking, output))
                .toUni();
    }

    private Multi<GetBoostedListOutput> getCurrentBoosting(Linking linking) {
        ShopApiCommonParam commonParam = new ShopApiCommonParam(
                Integer.parseInt(linking.partnerId),
                linking.partnerSecret,
                linking.accessToken,
                Integer.parseInt(linking.shopId)
        );
        return productService.getBoostedList(commonParam).toMulti()
                .filter(output -> {
                    if (output.getError() != null && output.getError().length() > 0) {
                        final String MSG =
                                "Error while extracting boosted list: "
                                        + output.getError() + " - "
                                        + output.getMessage();
                        log.error(MSG);
                        throw new RuntimeException(MSG);
                    } else {
                        log.info("Boosted list output: " + output);
                    }
                    return true;
                });
    }

    /**
     * TODO: Optimization
     *
     * @param boost  Boost records
     * @param output Current boosting list
     * @return return 5 items to be boosted
     */
    private List<String> getItemToBoost(Boost boost, GetBoostedListOutput output) {
        // Exempt current boosting list from configured list
        Map<String, Integer> successAttempt = boost.successAttempt;
        List<Long> boostingIds = output.getResponse().getItem_list().stream()
                .map(GetBoostedListOutput.Item::getItem_id).collect(Collectors.toList());

        // if currently boosting number >= 5, just attempt to boost 1 item
        // as there is no way to tell how many slots the shop currently has
        // otherwise, make it 5 since it is the default no of slot
        final int NO_OF_SLOT_DEFAULT = 5;
        int NO_OF_ITEM_TO_BOOST = boostingIds.size() >= NO_OF_SLOT_DEFAULT ? 1 : NO_OF_SLOT_DEFAULT - boostingIds.size();

        Map<String, Integer> summary = new HashMap<>();
        for (Map.Entry<String, Integer> entry : successAttempt.entrySet()) {
            Long itemId = Long.parseLong(entry.getKey());
            if (!boostingIds.contains(itemId)) {
                summary.put(entry.getKey(), entry.getValue());
            }
        }

        LinkedHashMap<String, Integer> linkedHashMap = summary.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()))
                .limit(NO_OF_ITEM_TO_BOOST)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        log.info("item to boost: " + linkedHashMap);
        return new ArrayList<>(linkedHashMap.keySet());
    }

    private Multi<BoostItemOutput> boostItem(Linking linking, List<String> ids) {
        ShopApiCommonParam commonParam = new ShopApiCommonParam(
                Integer.parseInt(linking.partnerId),
                linking.partnerSecret,
                linking.accessToken,
                Integer.parseInt(linking.shopId)
        );

        List<Long> itemIds = ids.stream().map(Long::parseLong).collect(Collectors.toList());
        BoostItemInput input = new BoostItemInput(itemIds);
        return productService.boostItem(commonParam, input).toMulti()
                .filter(output -> {
                    if (output.getError() != null && output.getError().length() > 0) {
                        final String MSG =
                                "Error while boosting item: "
                                        + output.getError() + " - "
                                        + output.getMessage();
                        log.error(MSG);
                        throw new RuntimeException(MSG);
                    }
                    return true;
                });
    }

    private boolean recordSuccessfulBoost(Linking linking, BoostItemOutput output) {
        List<Long> successIds = output.getResponse().getSuccess_list().getItem_id_list();
        log.info("Newly boosted ids: " + successIds);
        if (output.getResponse().getSuccess_list().getItem_id_list().isEmpty()) {
            return true;
        }
        return boostRepository.saveSuccessAttempt(linking.platform, linking.shopId, successIds);
    }

}

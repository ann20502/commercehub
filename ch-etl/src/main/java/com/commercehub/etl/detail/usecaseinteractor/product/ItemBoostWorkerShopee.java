package com.commercehub.etl.detail.usecaseinteractor.product;

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
                    log.info("Boosted list output: " + output);
                    if (output.getError() != null && output.getError().length() > 0) {
                        log.error("Error while extracting boosted list: " + output.getMessage());
                        log.error(output);
                        throw new RuntimeException("Error while extracting boosted list: " + output.getMessage());
                    }
                    return true;
                });
    }

    /**
     * TODO: Optimization
     *
     * @param boost Boost records
     * @param output Current boosting list
     * @return return 5 items to be boosted
     */
    private List<String> getItemToBoost(Boost boost, GetBoostedListOutput output) {
        // Exempt current boosting list from configured list
        Map<String,Integer> successAttempt = boost.successAttempt;
        List<Long> boostingIds = output.getResponse().getItem_list().stream()
                .map(GetBoostedListOutput.Item::getItem_id).collect(Collectors.toList());

        Map<String, Long> summary = new HashMap<>();
        for (Map.Entry<String,Integer> entries : successAttempt.entrySet()) {
            Long itemId = Long.parseLong(entries.getKey());
            if (!boostingIds.contains(itemId)) {
                summary.put(entries.getKey(), itemId);
            }
        }

        LinkedHashMap<String, Long> linkedHashMap = summary.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

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
                    log.info("Boost item output: " + output);
                    if (output.getError() != null && output.getError().length() > 0) {
                        log.error("Error while boosting items: " + output.getMessage());
                        log.error(output);
                        throw new RuntimeException("Error while boosting items: " + output.getMessage());
                    }
                    return true;
                });
    }

    private boolean recordSuccessfulBoost(Linking linking, BoostItemOutput output) {
        List<Long> successIds = output.getResponse().getSuccess_list().getItem_id_list();
        if ( output.getResponse().getSuccess_list().getItem_id_list().isEmpty() ) {
            return true;
        }
        return boostRepository.saveSuccessAttempt(linking.platform, linking.shopId, successIds);
    }

}

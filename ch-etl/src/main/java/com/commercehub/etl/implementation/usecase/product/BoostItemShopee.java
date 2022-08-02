package com.commercehub.etl.implementation.usecase.product;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.product.Boost;
import com.commercehub.etl.domain.entity.schduler.ShopIdentifier;
import com.commercehub.etl.domain.repository.BoostRepository;
import com.commercehub.etl.domain.repository.LinkingRepository;
import com.commercehub.etl.domain.usecase.product.BoostItem;
import com.commercehub.rest.shopee.ShopeeProductService;
import com.commercehub.rest.shopee.input.BoostItemInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.BoostItemOutput;
import com.commercehub.rest.shopee.output.GetBoostedListOutput;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Dependent
public class BoostItemShopee implements BoostItem {

    @Inject
    LinkingRepository linkingRepository;

    @Inject
    BoostRepository boostRepository;

    @Inject
    @RestClient
    ShopeeProductService productService;

    @Inject
    Logger log;

    @Override
    public Multi<Boolean> boost() {
        return getLinking().flatMap(this::boost);
    }

    private Multi<Boolean> boost(Linking linking) {
        return Multi.createFrom()
                .item(() -> boostRepository.getSetting(linking.getPlatform(), linking.getShopId()))
                .filter(Objects::nonNull)
                .flatMap(settings ->
                        getCurrentBoosting(linking)
                                .map(boosting -> Tuple.of(settings, boosting)))
                .map(settingsBoostingTuple -> getItemToBoost(settingsBoostingTuple.x(), settingsBoostingTuple.y()))
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> boostItem(linking, ids))
                .map(output -> recordSuccessfulBoost(linking, output));
    }

    private Multi<Linking> getLinking() {
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, true, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.getPlatform(), linking.getShopId()))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()));
    }

    private Multi<GetBoostedListOutput> getCurrentBoosting(Linking linking) {
        ShopApiCommonParam commonParam = new ShopApiCommonParam(
                Integer.parseInt(linking.getPartnerId()),
                linking.getPartnerSecret(),
                linking.getAccessToken(),
                Integer.parseInt(linking.getShopId())
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

    // TODO: Optimization
    // Bypassed ...
    private List<String> getItemToBoost(Boost boost, GetBoostedListOutput output) {
        // Exempt current boosting list from configured list
        Map<String,Long> settings = boost.getSettings();
        List<Long> boostingIds = output.getResponse().getItem_list().stream()
                .map(GetBoostedListOutput.Item::getItem_id).collect(Collectors.toList());

        Map<String, Long> summary = new HashMap<>();
        for (Map.Entry<String, Long> entries : settings.entrySet()) {
            Long itemId = Long.parseLong(entries.getKey());
            if (!boostingIds.contains(itemId)) {
                summary.put(entries.getKey(), itemId);
            }
        }

        // Return max 10 items based on no of successful attempt
        // This way will boost item equally
        LinkedHashMap<String, Long> linkedHashMap = summary.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return new ArrayList<>(linkedHashMap.keySet());
    }

    private Multi<BoostItemOutput> boostItem(Linking linking, List<String> ids) {
        ShopApiCommonParam commonParam = new ShopApiCommonParam(
                Integer.parseInt(linking.getPartnerId()),
                linking.getPartnerSecret(),
                linking.getAccessToken(),
                Integer.parseInt(linking.getShopId())
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
        return boostRepository.saveSuccessAttempt(linking.getPlatform(), linking.getShopId(), successIds);
    }

}

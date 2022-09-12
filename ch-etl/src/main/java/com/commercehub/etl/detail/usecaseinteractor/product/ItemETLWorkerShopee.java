package com.commercehub.etl.detail.usecaseinteractor.product;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.product.ItemShopee;
import com.commercehub.etl.core.repository.ProductRepositoryShopee;
import com.commercehub.etl.core.usecase.product.ItemETLWorker;
import com.commercehub.etl.detail.common.ETLUtils;
import com.commercehub.etl.detail.repository.product.transformer.ItemShopeeTransformer;
import com.commercehub.rest.shopee.ShopeeProductService;
import com.commercehub.rest.shopee.input.GetItemBaseInfoInput;
import com.commercehub.rest.shopee.input.GetItemListInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetItemBaseInfoOutput;
import com.commercehub.rest.shopee.output.GetItemListOutput;
import io.reactivex.Observable;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.multi.MultiRxConverters;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Dependent
public class ItemETLWorkerShopee implements ItemETLWorker {

    @Inject
    @RestClient
    ShopeeProductService productService;

    @Inject
    ProductRepositoryShopee productRepository;

    @Inject
    Logger log;

    @Override
    public Uni<Boolean> extractTransformLoad(Linking input) {
        return Multi.createFrom().item(input)
                .flatMap(
                        linking -> Multi.createFrom().converter(
                                MultiRxConverters.fromObservable(),
                                etlItemUpdate(linking)
                        )
                )
                .collect().asList()
                .map(results -> {
                    // fine if all good, error otherwise
                    for (boolean result : results) {
                        if (!result) return false;
                    }
                    return true;
                });
    }

    private Observable<Boolean> etlItemUpdate(Linking linking) {
        return getAllItemWithUpdate(linking)
                .flatMap(output -> Observable.fromIterable(output.getResponse().getItem_list()))
                .map(ItemShopeeTransformer::from)
                .buffer(20)
                .map(items -> this.save(linking, items));
    }

    private Observable<GetItemBaseInfoOutput> getAllItemWithUpdate(Linking linking) {
        return getItemList(linking)
                .map(output -> output.getResponse().getItems())
                .filter(items -> !items.isEmpty())
                .flatMap(items -> getItemWithUpdate(linking, items))
                .flatMap(Observable::fromIterable)
                .buffer(20)
                .flatMap(ids -> getItemDetail(linking, ids));
    }

    private Observable<GetItemListOutput> getItemList(Linking linking) {
        final int PAGE_SIZE = 20;
        return Observable
                .fromIterable(ETLUtils.getIndex(PAGE_SIZE, 10))
                .concatMap(index -> {
                    ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            Integer.parseInt(linking.partnerId),
                            linking.partnerSecret,
                            linking.accessToken,
                            Integer.parseInt(linking.shopId)
                    );

                    GetItemListInput input = new GetItemListInput(
                            index,
                            PAGE_SIZE,
                            null, null,
                            Arrays.asList(
                                    GetItemListInput.ItemStatusField.NORMAL,
                                    GetItemListInput.ItemStatusField.BANNED,
                                    GetItemListInput.ItemStatusField.DELETED,
                                    GetItemListInput.ItemStatusField.UNLIST
                            )
                    );

                    return productService.getItemList(commonParam, input).toMulti()
                            .convert().with(MultiRxConverters.toObservable());
                })
                .filter(output -> {
                    log.info("Get item list output: " + output);
                    if (output.getError() != null && output.getError().length() > 0) {
                        log.error("Error while getting item list: " + output.getMessage());
                        log.error(output);
                        throw new RuntimeException("Error while getting item list: " + output.getMessage());
                    }
                    return true;
                })
                .takeUntil(output -> !output.getResponse().isHas_next_page());
    }

    private Observable<List<Long>> getItemWithUpdate(Linking linking, List<GetItemListOutput.Item> input) {
        return Observable.just(input)
                .map(items -> {
                    List<Long> ids = new ArrayList<>();
                    for (GetItemListOutput.Item item : items) {
                        ids.add(item.getItem_id());
                    }
                    List<ItemShopee> dbItems = productRepository.getItemWithLatestUpdateTimestamp(
                            linking.platform,
                            linking.shopId,
                            ids
                    );

                    List<Long> result = new ArrayList<>();
                    for (GetItemListOutput.Item item : items) {
                        boolean found = false;
                        for (ItemShopee dbItem : dbItems) {
                            if (item.getItem_id() == dbItem.itemId) {
                                found = true;
                                if (item.getUpdate_time() > dbItem.updateTime.getEpochSecond()) {
                                    result.add(item.getItem_id());
                                }
                                break;
                            }
                        }
                        if (!found) {
                            result.add(item.getItem_id());
                        }
                    }
                    return result;
                });
    }

    private Observable<GetItemBaseInfoOutput> getItemDetail(Linking linking, List<Long> itemIds) {
        return Observable.just(itemIds)
                .flatMap(items -> {
                    ShopApiCommonParam commonParam = new ShopApiCommonParam(
                            Integer.parseInt(linking.partnerId),
                            linking.partnerSecret,
                            linking.accessToken,
                            Integer.parseInt(linking.shopId)
                    );

                    GetItemBaseInfoInput input = new GetItemBaseInfoInput(
                            items, false, false
                    );

                    return productService.getItemBaseInfo(commonParam, input).toMulti()
                            .convert().with(MultiRxConverters.toObservable());
                })
                .filter(output -> {
                    log.info("Item detail output: " + output);
                    if (output.getError() != null && output.getError().length() > 0) {
                        log.error("Error while extracting item detail: " + output.getMessage());
                        log.error(output);
                        throw new RuntimeException("Error while extracting item detail: " + output.getMessage());
                    }
                    return true;
                });
    }

    private boolean save(Linking linking, List<ItemShopee> items) {
        return productRepository.saveItemUpdate(
                linking.platform, linking.shopId, items
        );
    }

}

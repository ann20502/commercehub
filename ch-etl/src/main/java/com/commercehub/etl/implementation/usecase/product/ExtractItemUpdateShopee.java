package com.commercehub.etl.implementation.usecase.product;

import com.commercehub.etl.common.ETLUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.product.*;
import com.commercehub.etl.domain.entity.schduler.ShopIdentifier;
import com.commercehub.etl.domain.repository.LinkingRepository;
import com.commercehub.etl.domain.repository.ProductRepository;
import com.commercehub.etl.domain.usecase.product.ExtractItemUpdate;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Dependent
public class ExtractItemUpdateShopee implements ExtractItemUpdate {

    @Inject
    LinkingRepository linkingRepository;

    @Inject
    @RestClient
    ShopeeProductService productService;

    @Inject
    ProductRepository productRepository;

    @Inject
    Logger log;

    @Override
    public Uni<Boolean> extract() {
        return getLinking()
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

    private Multi<Linking> getLinking() {
        return Multi.createFrom()
                .iterable(linkingRepository.getAll(Linking.STATUS_ACTIVE, true, true))
                .collect().asMap(linking -> new ShopIdentifier(linking.getPlatform(), linking.getShopId()))
                .toMulti()
                .flatMap(map -> Multi.createFrom().iterable(map.values()));
    }

    private Observable<Boolean> etlItemUpdate(Linking linking) {
        return getAllItemWithUpdate(linking)
                .flatMap(output -> Observable.fromIterable(output.getResponse().getItem_list()))
                .map(this::transform)
                .buffer(20)
                .map(items -> this.save(linking, items));
    }

    private Observable<GetItemBaseInfoOutput> getAllItemWithUpdate(Linking linking) {
        return getItemList(linking)
                .map(output -> output.getResponse().getItems())
                .filter(ids -> !ids.isEmpty())
                .flatMap(ids -> getItemWithUpdate(linking, ids))
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
                            Integer.parseInt(linking.getPartnerId()),
                            linking.getPartnerSecret(),
                            linking.getAccessToken(),
                            Integer.parseInt(linking.getShopId())
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
                    List<Item> dbItems = productRepository.getItemWithLatestUpdateTimestamp(
                            linking.getPlatform(),
                            linking.getShopId(),
                            ids
                    );

                    List<Long> result = new ArrayList<>();
                    for (GetItemListOutput.Item item : items) {
                        boolean found = false;
                        for (Item dbItem : dbItems) {
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
                            Integer.parseInt(linking.getPartnerId()),
                            linking.getPartnerSecret(),
                            linking.getAccessToken(),
                            Integer.parseInt(linking.getShopId())
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

    private Item transform(GetItemBaseInfoOutput.Item item) {
        List<Item.Attribute> attributes = new ArrayList<>();
        if (item.getAttribute_list() != null) {
            for (GetItemBaseInfoOutput.Attribute attribute : item.getAttribute_list()) {
                List<Item.AttributeValue> values = new ArrayList<>();
                for (GetItemBaseInfoOutput.AttributeValue value : attribute.getAttribute_value_list()) {
                    values.add(
                            new AttributeValueBuilder()
                                    .setAttributeValueId(value.getValue_id())
                                    .setOriginalValueName(value.getOriginal_value_name())
                                    .setValueUnit(value.getValue_unit())
                                    .createAttributeValue()
                    );
                }

                attributes.add(
                        new AttributeBuilder()
                                .setAttributeId(attribute.getAttribute_id())
                                .setOriginalAttributeName(attribute.getOriginal_attribute_name())
                                .setIs_mandatory(attribute.isIs_mandatory())
                                .setAttributeValues(values)
                                .createAttribute()
                );
            }
        }

        List<Item.Price> prices = new ArrayList<>();
        if (item.getPrice_info() != null) {
            for (GetItemBaseInfoOutput.PriceInfo priceInfo : item.getPrice_info()) {
                prices.add(
                        new PriceBuilder()
                                .setCurrency(priceInfo.getCurrency())
                                .setOriginalPrice(priceInfo.getOriginal_price())
                                .setCurrentPrice(priceInfo.getCurrent_price())
                                .setInflatedPriceOfCurrentPrice(priceInfo.getInflated_price_of_current_price())
                                .setSipItemPrice(priceInfo.getSip_item_price())
                                .setSipItemPriceSource(priceInfo.getSip_item_price_source())
                                .createPrice()
                );
            }
        }

        Item.Image image =
                item.getImage() == null ? null :
                        new Item.Image(
                                item.getImage().getImage_url_list(),
                                item.getImage().getImage_id_list()
                        );

        Item.Dimension dimension =
                item.getDimension() == null ? null :
                        new Item.Dimension(
                                item.getDimension().getPackage_length(),
                                item.getDimension().getPackage_width(),
                                item.getDimension().getPackage_height()
                        );

        Item.PreOrder preOrder = item.getPre_order() == null ? null :
                new Item.PreOrder(
                        item.getPre_order().isIs_pre_order(),
                        item.getPre_order().getDays_to_ship()
                );

        List<Item.Wholesale> wholesales = new ArrayList<>();
        if ( item.getWholesales() != null ) {
            for (GetItemBaseInfoOutput.Wholesales wholesale : item.getWholesales()) {
                wholesales.add(
                        new WholesaleBuilder()
                                .setMax_count(wholesale.getMin_count())
                                .setMin_count(wholesale.getMax_count())
                                .setUnitPrice(wholesale.getUnit_price())
                                .setInflatedPriceOfUnitPrice(wholesale.getInflated_price_of_unit_price())
                                .createWholesale()
                );
            }
        }

        List<Item.Video> videos = new ArrayList<>();
        if ( item.getVideo_info() != null ) {
            for (GetItemBaseInfoOutput.VideoInfo videoInfo : item.getVideo_info()) {
                videos.add(
                        new Item.Video(
                                videoInfo.getVideo_url(),
                                videoInfo.getThumbnail_url(),
                                videoInfo.getDuration()
                        )
                );
            }
        }

        Item.Brand brand = item.getBrand() == null ? null :
                new Item.Brand(
                        item.getBrand().getBrand_id(),
                        item.getBrand().getOriginal_brand_name()
                );

        GetItemBaseInfoOutput.StockInfoV2 stockInfoV2 = item.getStock_info_v2();

        Item.StockSummary stockSummary = stockInfoV2 == null ? null :
                new Item.StockSummary(
                        stockInfoV2.getSummary_info().getTotal_reserved_stock(),
                        stockInfoV2.getSummary_info().getTotal_available_stock()
                );

        Item.StockSeller stockSeller = stockInfoV2 == null ? null :
                new Item.StockSeller(
                        stockInfoV2.getSeller_stock().getLocation_id(),
                        stockInfoV2.getSeller_stock().getStock()
                );

        Item.StockShopee stockShopee = stockInfoV2 == null ? null :
                new Item.StockShopee(
                        stockInfoV2.getShopee_stock().getLocation_id(),
                        stockInfoV2.getShopee_stock().getStock()
                );

        return new ItemBuilder()
                .setItemId(item.getItem_id())
                .setCategoryId(item.getCategory_id())
                .setName(item.getItem_name())
                .setDescription(item.getDescription())
                .setSku(item.getItem_sku())
                .setCreateTime(item.getCreate_time())
                .setUpdateTime(item.getUpdate_time())
                .setExtractTime(Instant.now())
                .setAttributes(attributes)
                .setPrices(prices)
                .setImage(image)
                .setWeight(item.getWeight())
                .setDimension(dimension)
                .setPreOrder(preOrder)
                .setWholesales(wholesales)
                .setCondition(item.getCondition())
                .setSizeChart(item.getSize_chart())
                .setItemStatus(item.getItem_status())
                .setHasModel(item.isHas_model())
                .setPromotionId(item.getPromotion_id())
                .setVideos(videos)
                .setBrand(brand)
                .setItemDangerous(item.getItem_dangerous())
                .setStockSummary(stockSummary)
                .setStockSeller(stockSeller)
                .setStockShopee(stockShopee)
                .createItem();
    }

    private boolean save(Linking linking, List<Item> items) {
        return productRepository.saveItemUpdate(
                linking.getPlatform(), linking.getShopId(), items
        );
    }

}

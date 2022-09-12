package com.commercehub.etl.detail.repository.product.transformer;

import com.commercehub.etl.core.entity.product.ItemShopee;
import com.commercehub.etl.core.entitybuilder.product.shopee.*;
import com.commercehub.rest.shopee.output.GetItemBaseInfoOutput;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ItemShopeeTransformer {

    public static ItemShopee from(GetItemBaseInfoOutput.Item item) {
        List<ItemShopee.Attribute> attributes = new ArrayList<>();
        if (item.getAttribute_list() != null) {
            for (GetItemBaseInfoOutput.Attribute attribute : item.getAttribute_list()) {
                List<ItemShopee.AttributeValue> values = new ArrayList<>();
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

        List<ItemShopee.Price> prices = new ArrayList<>();
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

        ItemShopee.Image image =
                item.getImage() == null ? null :
                        new ItemShopee.Image(
                                item.getImage().getImage_url_list(),
                                item.getImage().getImage_id_list()
                        );

        ItemShopee.Dimension dimension =
                item.getDimension() == null ? null :
                        new ItemShopee.Dimension(
                                item.getDimension().getPackage_length(),
                                item.getDimension().getPackage_width(),
                                item.getDimension().getPackage_height()
                        );

        ItemShopee.PreOrder preOrder = item.getPre_order() == null ? null :
                new ItemShopee.PreOrder(
                        item.getPre_order().isIs_pre_order(),
                        item.getPre_order().getDays_to_ship()
                );

        List<ItemShopee.Wholesale> wholesales = new ArrayList<>();
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

        List<ItemShopee.Video> videos = new ArrayList<>();
        if ( item.getVideo_info() != null ) {
            for (GetItemBaseInfoOutput.VideoInfo videoInfo : item.getVideo_info()) {
                videos.add(
                        new ItemShopee.Video(
                                videoInfo.getVideo_url(),
                                videoInfo.getThumbnail_url(),
                                videoInfo.getDuration()
                        )
                );
            }
        }

        ItemShopee.Brand brand = item.getBrand() == null ? null :
                new ItemShopee.Brand(
                        item.getBrand().getBrand_id(),
                        item.getBrand().getOriginal_brand_name()
                );

        GetItemBaseInfoOutput.StockInfoV2 stockInfoV2 = item.getStock_info_v2();

        ItemShopee.StockSummary stockSummary = stockInfoV2 == null ? null :
                new ItemShopee.StockSummary(
                        stockInfoV2.getSummary_info().getTotal_reserved_stock(),
                        stockInfoV2.getSummary_info().getTotal_available_stock()
                );

        ItemShopee.StockSeller stockSeller = stockInfoV2 == null ? null :
                new ItemShopee.StockSeller(
                        stockInfoV2.getSeller_stock().getLocation_id(),
                        stockInfoV2.getSeller_stock().getStock()
                );

        ItemShopee.StockShopee stockShopee = stockInfoV2 == null ? null :
                new ItemShopee.StockShopee(
                        stockInfoV2.getShopee_stock().getLocation_id(),
                        stockInfoV2.getShopee_stock().getStock()
                );

        return new ItemShopeeBuilder()
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
                .createItemShopee();
    }

}

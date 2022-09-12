package com.commercehub.etl.detail.repository.product.transformer;

import com.commercehub.etl.core.entity.product.ItemShopee;
import com.commercehub.etl.detail.repository.product.BQItemShopee;
import com.commercehub.etl.detail.repository.product.builder.*;

import java.util.ArrayList;
import java.util.List;

public class BQItemShopeeTransformer {

    public static BQItemShopee from(ItemShopee item) {
        List<BQItemShopee.Attribute> attributes = new ArrayList<>();
        if (item.attributes != null) {
            for (ItemShopee.Attribute attribute : item.attributes) {
                List<BQItemShopee.AttributeValue> values = new ArrayList<>();
                for (ItemShopee.AttributeValue value : attribute.attributeValues) {
                    values.add(
                            new AttributeValueBuilder()
                                    .setAttributeValueId(value.attributeValueId)
                                    .setOriginalValueName(value.originalValueName)
                                    .setValueUnit(value.valueUnit)
                                    .createAttributeValue()
                    );
                }

                attributes.add(
                        new AttributeBuilder()
                                .setAttributeId(attribute.attributeId)
                                .setOriginalAttributeName(attribute.originalAttributeName)
                                .setIs_mandatory(attribute.is_mandatory)
                                .setAttributeValues(values)
                                .createAttribute()
                );
            }
        }

        List<BQItemShopee.Price> prices = new ArrayList<>();
        if (item.prices != null) {
            for (ItemShopee.Price price : item.prices) {
                prices.add(
                        new PriceBuilder()
                                .setCurrency(price.currency)
                                .setOriginalPrice(price.originalPrice)
                                .setCurrentPrice(price.currentPrice)
                                .setInflatedPriceOfCurrentPrice(price.inflatedPriceOfCurrentPrice)
                                .setSipItemPrice(price.sipItemPrice)
                                .setSipItemPriceSource(price.sipItemPriceSource)
                                .createPrice()
                );
            }
        }

        BQItemShopee.Image image =
                item.image == null ? null :
                        new BQItemShopee.Image(
                                item.image.imageUrls,
                                item.image.imageIds
                        );

        BQItemShopee.Dimension dimension =
                item.dimension == null ? null :
                        new BQItemShopee.Dimension(
                                item.dimension.length,
                                item.dimension.width,
                                item.dimension.height
                        );

        BQItemShopee.PreOrder preOrder = item.preOrder == null ? null :
                new BQItemShopee.PreOrder(
                        item.preOrder.isPreOrder,
                        item.preOrder.days_to_ship
                );

        List<BQItemShopee.Wholesale> wholesales = new ArrayList<>();
        if ( item.wholesales != null ) {
            for (ItemShopee.Wholesale wholesale : item.wholesales) {
                wholesales.add(
                        new WholesaleBuilder()
                                .setMax_count(wholesale.min_count)
                                .setMin_count(wholesale.max_count)
                                .setUnitPrice(wholesale.unitPrice)
                                .setInflatedPriceOfUnitPrice(wholesale.inflatedPriceOfUnitPrice)
                                .createWholesale()
                );
            }
        }

        List<BQItemShopee.Video> videos = new ArrayList<>();
        if ( item.videos != null ) {
            for (ItemShopee.Video videoInfo : item.videos) {
                videos.add(
                        new BQItemShopee.Video(
                                videoInfo.videoUrl,
                                videoInfo.thumbnailUrl,
                                videoInfo.duration
                        )
                );
            }
        }

        BQItemShopee.Brand brand = item.brand == null ? null :
                new BQItemShopee.Brand(
                        item.brand.brandId,
                        item.brand.originalBrandName
                );

        BQItemShopee.StockSummary stockSummary = item.stockSummary == null ? null :
                new BQItemShopee.StockSummary(
                        item.stockSummary.totalReservedStock,
                        item.stockSummary.totalAvailableStock
                );

        BQItemShopee.StockSeller stockSeller = item.stockSeller == null ? null :
                new BQItemShopee.StockSeller(
                        item.stockSeller.locationId,
                        item.stockSeller.stock
                );

        BQItemShopee.StockShopee stockShopee = item.stockShopee == null ? null :
                new BQItemShopee.StockShopee(
                        item.stockShopee.locationId,
                        item.stockShopee.stock
                );

        return new BQItemShopeeBuilder()
                .setItemId(item.itemId)
                .setCategoryId(item.categoryId)
                .setName(item.name)
                .setDescription(item.description)
                .setSku(item.sku)
                .setCreateTime(item.createTime)
                .setUpdateTime(item.updateTime)
                .setExtractTime(item.extractTime)
                .setAttributes(attributes)
                .setPrices(prices)
                .setImage(image)
                .setWeight(item.weight)
                .setDimension(dimension)
                .setPreOrder(preOrder)
                .setWholesales(wholesales)
                .setCondition(item.condition)
                .setSizeChart(item.sizeChart)
                .setItemStatus(item.itemStatus)
                .setHasModel(item.hasModel)
                .setPromotionId(item.promotionId)
                .setVideos(videos)
                .setBrand(brand)
                .setItemDangerous(item.itemDangerous)
                .setStockSummary(stockSummary)
                .setStockSeller(stockSeller)
                .setStockShopee(stockShopee)
                .createBQItemShopee();
    }

}

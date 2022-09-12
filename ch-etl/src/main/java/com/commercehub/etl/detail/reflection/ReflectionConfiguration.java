package com.commercehub.etl.detail.reflection;

import com.commercehub.api.common.Result;
import com.commercehub.common.Utils;
import com.commercehub.etl.detail.repository.boost.FSBoost;
import com.commercehub.etl.detail.repository.linking.FSLinking;
import com.commercehub.etl.detail.repository.order.BQOrderShopee;
import com.commercehub.etl.detail.repository.product.BQItemShopee;
import com.commercehub.etl.detail.repository.scheduler.FSTimedTask;
import com.commercehub.etl.detail.repository.shop.BQPerformanceShopee;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = {
        FSBoost.class,
        FSLinking.class,
        BQOrderShopee.class,
        BQOrderShopee.RecipientAddress.class,
        BQOrderShopee.ImageInfo.class,
        BQOrderShopee.Invoice.class,
        BQOrderShopee.Escrow.class,
        BQOrderShopee.Item.class,
        BQItemShopee.class,
        BQItemShopee.Attribute.class,
        BQItemShopee.AttributeValue.class,
        BQItemShopee.Price.class,
        BQItemShopee.Image.class,
        BQItemShopee.Dimension.class,
        BQItemShopee.PreOrder.class,
        BQItemShopee.Wholesale.class,
        BQItemShopee.Video.class,
        BQItemShopee.Brand.class,
        BQItemShopee.StockSummary.class,
        BQItemShopee.StockSeller.class,
        BQItemShopee.StockShopee.class,
        FSTimedTask.class,
        BQPerformanceShopee.class,
        Utils.BigDecimalAdapter.class,
        Utils.InstantAdapter.class,
        Result.class
})
public class ReflectionConfiguration {}

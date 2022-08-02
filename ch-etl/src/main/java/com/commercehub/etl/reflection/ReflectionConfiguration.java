package com.commercehub.etl.reflection;

import com.commercehub.common.Utils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingSimplified;
import com.commercehub.etl.domain.entity.order.Order;
import com.commercehub.etl.domain.entity.product.Boost;
import com.commercehub.etl.domain.entity.product.Item;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
import com.commercehub.etl.domain.entity.shop.Performance;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = {
        Linking.class,
        LinkingSimplified.class,
        TimedTask.class,
        Order.class,
        Order.RecipientAddress.class,
        Order.Item.class,
        Order.ImageInfo.class,
        Order.Invoice.class,
        Order.Escrow.class,
        Utils.BigDecimalAdapter.class,
        Utils.InstantAdapter.class,
        Performance.class,
        Boost.class,
        Item.class,
        Item.Attribute.class,
        Item.AttributeValue.class,
        Item.Price.class,
        Item.Image.class,
        Item.Dimension.class,
        Item.PreOrder.class,
        Item.Wholesale.class,
        Item.Video.class,
        Item.Brand.class,
        Item.StockSummary.class,
        Item.StockSeller.class,
        Item.StockShopee.class,

})
public class ReflectionConfiguration {}

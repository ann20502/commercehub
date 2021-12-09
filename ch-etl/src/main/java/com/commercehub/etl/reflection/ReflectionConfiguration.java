package com.commercehub.etl.reflection;

import com.commercehub.common.Utils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingSimplified;
import com.commercehub.etl.domain.entity.order.Order;
import com.commercehub.etl.domain.entity.schduler.TimedTask;
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
        Utils.InstantAdapter.class
})
public class ReflectionConfiguration {}

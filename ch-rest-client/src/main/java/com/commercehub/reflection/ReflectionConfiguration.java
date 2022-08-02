package com.commercehub.reflection;

import com.commercehub.rest.shopee.output.*;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                GetAccessTokenOutput.class,
                GetEscrowDetailOutput.class,
                GetOrderDetailOutput.class,
                GetOrderListOutput.class,
                GetShopInfoOutput.class,
                RefreshAccessTokenOutput.class,
                BoostItemOutput.class,
                GetBoostedListOutput.class,
                GetItemListOutput.class,
                GetItemBaseInfoOutput.class
        }
)
public class ReflectionConfiguration {}

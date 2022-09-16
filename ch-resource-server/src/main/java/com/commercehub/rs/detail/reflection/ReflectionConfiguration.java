package com.commercehub.rs.detail.reflection;

import com.commercehub.rs.core.entity.SaleByDate;
import com.commercehub.rs.core.entity.SaleByMonth;
import com.commercehub.rs.core.entity.ShopPerformance;
import com.commercehub.rs.core.entity.TopSelling;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                SaleByDate.class,
                SaleByMonth.class,
                ShopPerformance.class,
                ShopPerformance.Data.class,
                TopSelling.class,
                TopSelling.Item.class
        }
)
public class ReflectionConfiguration {}

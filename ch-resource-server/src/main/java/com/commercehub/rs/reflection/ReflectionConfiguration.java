package com.commercehub.rs.reflection;

import com.commercehub.rs.domain.entity.SalesByCalendar;
import com.commercehub.rs.domain.entity.SalesByShop;
import com.commercehub.rs.domain.entity.ShopPerformance;
import com.commercehub.rs.domain.entity.TopSelling;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                SalesByCalendar.class,
                SalesByShop.class,
                ShopPerformance.class,
                ShopPerformance.Data.class,
                TopSelling.class,
                TopSelling.Item.class
        }
)
public class ReflectionConfiguration {}

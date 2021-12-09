package com.commercehub.rs.reflection;

import com.commercehub.rs.domain.entity.SalesByCalendar;
import com.commercehub.rs.domain.entity.SalesByShop;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                SalesByCalendar.class,
                SalesByShop.class
        }
)
public class ReflectionConfiguration {}

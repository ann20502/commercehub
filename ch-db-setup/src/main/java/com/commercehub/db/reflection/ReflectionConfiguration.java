package com.commercehub.db.reflection;

import com.commercehub.db.domain.entity.Linking;
import io.quarkus.runtime.annotations.RegisterForReflection;

// Not necessary as the project only work in JVM (Due to flyway uses reflection)
@RegisterForReflection(targets = {
        Linking.class
})
public class ReflectionConfiguration {}

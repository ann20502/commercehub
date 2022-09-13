package com.commercehub.db.detail.reflection;

import com.commercehub.db.detail.repository.FSLinking;
import io.quarkus.runtime.annotations.RegisterForReflection;

// Not necessary as the project only work in JVM (Due to flyway uses reflection)
@RegisterForReflection(targets = {
        FSLinking.class
})
public class ReflectionConfiguration {}

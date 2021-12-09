package com.commercehub.reflection;

import com.commercehub.link.controller.AuthorizationRequestController;
import com.commercehub.link.controller.CancelAuthorizationRequestController;
import com.commercehub.linker.controller.GetPlatformController;
import com.commercehub.linker.domain.entity.ExistingLinking;
import com.commercehub.linker.domain.entity.Linking;
import com.commercehub.linker.domain.entity.Shop;
import com.commercehub.linker.domain.entity.TimedTask;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                AuthorizationRequestController.AuthorizationRedirectUri.class,
                CancelAuthorizationRequestController.CancelAuthorizationRedirectUri.class,
                com.commercehub.link.client.repository.Linking.class,
                com.commercehub.link.client.repository.LinkingRequest.class,
                ExistingLinking.class,
                Linking.class,
                Shop.class,
                TimedTask.class,
                GetPlatformController.Platform.class
        }
)
public class ReflectionConfiguration {}

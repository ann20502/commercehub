package com.commercehub.linker.detail.reflection;

import com.commercehub.link.controller.AuthorizationRequestController;
import com.commercehub.link.controller.CancelAuthorizationRequestController;
import com.commercehub.linker.core.entity.*;
import com.commercehub.linker.detail.repository.FSLinking;
import com.commercehub.linker.detail.repository.FSTimedTask;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
        targets = {
                AuthorizationRequestController.AuthorizationRedirectUri.class,
                CancelAuthorizationRequestController.CancelAuthorizationRedirectUri.class,
                com.commercehub.link.client.repository.Linking.class,
                com.commercehub.link.client.repository.LinkingRequest.class,
                FSLinking.class,
                FSTimedTask.class,
                Linking.class,
                LinkingAndSiteInfo.class,
                ModuleConfiguration.class,
                Shop.class,
                TimedTask.class
        }
)
public class ReflectionConfiguration {}

package com.commercehub.link.client.implementation;

import com.commercehub.link.client.AuthorizationRedirect;
import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.client.repository.LinkingRequest;
import com.commercehub.link.qualifier.LinkDefault;
import com.commercehub.link.qualifier.LinkPreferred;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Dependent
@LinkDefault
public class AuthorizationRedirectDefault implements AuthorizationRedirect {

    @Inject
    @LinkPreferred
    LinkClientConfiguration linkClientConfiguration;

    @Override
    public String redirectUri() {
        return linkClientConfiguration.apiBasePath();
    }

    @Override
    public Map<String, Object> param(LinkingRequest request) {
        return new HashMap<>();
    }

}

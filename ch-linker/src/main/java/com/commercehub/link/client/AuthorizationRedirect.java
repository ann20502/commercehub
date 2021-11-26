package com.commercehub.link.client;

import com.commercehub.link.client.repository.LinkingRequest;

import java.util.Map;

public interface AuthorizationRedirect {

    String redirectUri();

    Map<String,Object> param(LinkingRequest request);

}

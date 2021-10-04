package com.commercehub.link.client;

import java.util.Map;

public interface AuthorizationRedirect {

    String redirectUri();

    Map<String,Object> param();

}

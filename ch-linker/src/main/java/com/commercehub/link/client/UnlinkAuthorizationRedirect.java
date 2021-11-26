package com.commercehub.link.client;

import java.util.Map;

public interface UnlinkAuthorizationRedirect {

    String redirectUri();

    Map<String,Object> param(String documentId);

}

package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationRedirect;
import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import com.commercehub.link.client.repository.LinkingRequest;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.CookieImpl;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

@Dependent
@LinkQualifier("shopee")
public class AuthorizationRedirectShopee implements AuthorizationRedirect {

    private final String PARAM_PARTNER_ID = "partner_id";
    private final String PARAM_REDIRECT = "redirect";
    private final String PARAM_SIGN = "sign";
    private final String PARAM_TIMESTAMP = "timestamp";

    @Inject
    @LinkPreferred
    LinkClientConfiguration configuration;

    @Inject
    HttpServerRequest request;

    @Inject
    UriInfo uriInfo;

    @Inject
    HttpServerResponse response;

    private final String PATH_SHOP_AUTHORIZATION = "/shop/auth_partner";

    @Override
    public String redirectUri() {
        final String versionPath = configuration.apiVersionPath().isPresent() ? configuration.apiVersionPath().get() : "";
        return configuration.apiBasePath() + versionPath + PATH_SHOP_AUTHORIZATION;
    }

    @Override
    public Map<String,Object> param(LinkingRequest request) {
        final String clientId = request.getPartnerId();
        final String clientSecret = request.getPartnerSecret();

        final String targetPath = configuration.apiVersionPath().isPresent() ?
                configuration.apiVersionPath().get() + PATH_SHOP_AUTHORIZATION : PATH_SHOP_AUTHORIZATION;

//        final String id = UUID.randomUUID().toString(); setCookie(id);
        final String redirectUri = getRedirectUri(request.getId());

        return ShopeeLinkClientUtils.getRedirectParam(clientId, clientSecret, targetPath, redirectUri);
    }

    private String getRedirectUri(String id) {
        String redirectPath = configuration.redirectPath();
        String finalRedirectPath = redirectPath.startsWith("/") ? redirectPath.substring(1) : redirectPath;
        return uriInfo.getBaseUri().toString() + finalRedirectPath + "/" + id;
    }

    private void setCookie(String id) {
        String redirectUri = request.getParam("redirectUri");
        Cookie cookie = new CookieImpl(id, redirectUri);
        cookie.setPath(getCookiePath());
        response.addCookie(cookie);
    }

    /*
        Not require cos it can't support cross domain, for example:
        -- localhost & 127.0.0.1
     */
    private String getCookieDomain() {
        System.out.println("Request URI Host: " + uriInfo.getRequestUri().getHost());
        return uriInfo.getRequestUri().getHost();
    }

    private String getCookiePath() {
        return configuration.redirectPath();
    }

}

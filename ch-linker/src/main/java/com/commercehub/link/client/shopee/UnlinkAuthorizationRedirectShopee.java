package com.commercehub.link.client.shopee;

import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.client.UnlinkAuthorizationRedirect;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.CookieImpl;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.Map;
import java.util.UUID;

@Dependent
@LinkQualifier("shopee")
public class UnlinkAuthorizationRedirectShopee implements UnlinkAuthorizationRedirect {

    @Inject
    @LinkPreferred
    LinkClientConfiguration configuration;

    @Inject
    HttpServerRequest request;

    @Inject
    UriInfo uriInfo;

    @Inject
    HttpServerResponse response;

    private final String PATH_SHOP_CANCEL_AUTHORIZATION = "/shop/cancel_auth_partner";

    @Override
    public String redirectUri() {
        final String versionPath = configuration.apiVersionPath().isPresent() ? configuration.apiVersionPath().get() : "";
        return configuration.apiBasePath() + versionPath + PATH_SHOP_CANCEL_AUTHORIZATION;
    }

    @Override
    public Map<String, Object> param() {
        final String clientId = configuration.clientId();
        final String clientSecret = configuration.clientSecret();

        final String targetPath = configuration.apiVersionPath().isPresent() ?
                configuration.apiVersionPath().get() + PATH_SHOP_CANCEL_AUTHORIZATION : PATH_SHOP_CANCEL_AUTHORIZATION;

        final String id = UUID.randomUUID().toString(); setCookie(id);
        final String redirectUri = getRedirectUri(id);

        return ShopeeLinkClientUtils.getRedirectParam(clientId, clientSecret, targetPath, redirectUri);
    }

    private String getRedirectUri(String id) {
        String redirectPath = configuration.unlinkRedirectPath();
        String finalRedirectPath = redirectPath.startsWith("/") ? redirectPath.substring(1) : redirectPath;
        return uriInfo.getBaseUri().toString() + finalRedirectPath + "/" + id;
    }

    private void setCookie(String id) {
        String redirectUri = request.getParam("redirectUri");
        Cookie cookie = new CookieImpl(id, redirectUri);
        cookie.setPath(getCookiePath());
        response.addCookie(cookie);
    }

    private String getCookiePath() {
        return configuration.unlinkRedirectPath();
    }

}

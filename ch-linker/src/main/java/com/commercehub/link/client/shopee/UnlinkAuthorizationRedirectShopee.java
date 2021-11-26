package com.commercehub.link.client.shopee;

import com.commercehub.link.client.LinkClientConfiguration;
import com.commercehub.link.client.UnlinkAuthorizationRedirect;
import com.commercehub.link.client.repository.Linking;
import com.commercehub.link.client.repository.LinkingRepository;
import com.commercehub.link.qualifier.LinkPreferred;
import com.commercehub.link.qualifier.LinkQualifier;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.CookieImpl;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;
import java.util.Map;
import java.util.UUID;

@Dependent
@LinkQualifier("shopee")
public class UnlinkAuthorizationRedirectShopee implements UnlinkAuthorizationRedirect {

    @ConfigProperty(name = "quarkus.http.root-path")
    String rootPath;

    @Inject
    @LinkPreferred
    LinkClientConfiguration configuration;

    @Inject
    LinkingRepository repository;

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
    public Map<String, Object> param(String documentId) {
        Linking linking = repository.get(documentId);

        final String clientId = linking.getPartnerId();
        final String clientSecret = linking.getPartnerSecret();

        final String targetPath = configuration.apiVersionPath().isPresent() ?
                configuration.apiVersionPath().get() + PATH_SHOP_CANCEL_AUTHORIZATION : PATH_SHOP_CANCEL_AUTHORIZATION;

        setCookie(documentId);
        final String redirectUri = getRedirectUri(documentId);

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
        String finalRootPath = rootPath == null || rootPath.isEmpty() ? "" : rootPath;
        String unlinkRedirectPath = configuration.unlinkRedirectPath();
        return finalRootPath +  unlinkRedirectPath;
    }

}

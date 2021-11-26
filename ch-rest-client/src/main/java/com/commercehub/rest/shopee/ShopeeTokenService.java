package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeClientResponseFilter;
import com.commercehub.rest.shopee.filter.ShopeePublicApiClientRequestFilter;
import com.commercehub.rest.shopee.input.GetAccessTokenInput;
import com.commercehub.rest.shopee.input.PublicApiCommonParam;
import com.commercehub.rest.shopee.input.RefreshAccessTokenInput;
import com.commercehub.rest.shopee.output.GetAccessTokenOutput;
import com.commercehub.rest.shopee.output.RefreshAccessTokenOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeePublicApiClientRequestFilter.class)
public interface ShopeeTokenService {

    @POST
    @Path("/api/v2/auth/token/get")
    Uni<GetAccessTokenOutput> getAccessToken(@BeanParam PublicApiCommonParam commonParam, GetAccessTokenInput requestInput);

    @POST
    @Path("/api/v2/auth/access_token/get")
    Uni<RefreshAccessTokenOutput> refreshAccessToken(@BeanParam PublicApiCommonParam commonParam, RefreshAccessTokenInput refreshAccessTokenInput);

}

package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeePublicApiClientRequestFilter;
import com.commercehub.rest.shopee.input.GetAccessTokenInput;
import com.commercehub.rest.shopee.output.GetAccessTokenOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeePublicApiClientRequestFilter.class)
public interface ShopeeTokenService {

    @POST
    @Path("/api/v2/auth/token/get")
    Uni<GetAccessTokenOutput> getAccessToken(GetAccessTokenInput requestInput);

}

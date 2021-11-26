package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeShopApiClientRequestFilter;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetShopInfoOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeeShopApiClientRequestFilter.class)
public interface ShopeeShopService {

    @GET
    @Path("/api/v2/shop/get_shop_info")
    Uni<GetShopInfoOutput> getShopInfo(@BeanParam ShopApiCommonParam commonParam);

}

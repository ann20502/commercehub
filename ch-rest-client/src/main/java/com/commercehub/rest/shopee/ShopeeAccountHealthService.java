package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeShopApiClientRequestFilter;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetShopPerformanceOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeeShopApiClientRequestFilter.class)
public interface ShopeeAccountHealthService {

    @GET
    @Path("/api/v2/account_health/shop_performance")
    Uni<GetShopPerformanceOutput> getShopPerformance(@BeanParam ShopApiCommonParam commonParam);

}

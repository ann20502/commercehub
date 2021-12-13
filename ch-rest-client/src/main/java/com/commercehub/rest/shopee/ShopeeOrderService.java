package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeShopApiClientRequestFilter;
import com.commercehub.rest.shopee.input.GetOrderDetailInput;
import com.commercehub.rest.shopee.input.GetOrderListInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetOrderDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderListOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeeShopApiClientRequestFilter.class)
public interface ShopeeOrderService {

    @GET
    @Path("/api/v2/order/get_order_list")
    Uni<GetOrderListOutput> getOrderList(
            @BeanParam ShopApiCommonParam commonParam,
            @BeanParam GetOrderListInput getOrderListInput
    );

    @GET
    @Path("/api/v2/order/get_order_detail")
    Uni<GetOrderDetailOutput> getOrderDetail(
            @BeanParam ShopApiCommonParam commonParam,
            @BeanParam GetOrderDetailInput getOrderDetailInput
    );

}

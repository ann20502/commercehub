package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeShopApiClientRequestFilter;
import com.commercehub.rest.shopee.input.GetEscrowDetailInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetEscrowDetailOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeeShopApiClientRequestFilter.class)
public interface ShopeePaymentService {

    @GET
    @Path("/api/v2/payment/get_escrow_detail")
    Uni<GetEscrowDetailOutput> getEscrowDetail(@BeanParam ShopApiCommonParam commonParam, @BeanParam GetEscrowDetailInput getEscrowDetailInput);

}

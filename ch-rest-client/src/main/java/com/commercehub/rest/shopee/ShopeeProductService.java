package com.commercehub.rest.shopee;

import com.commercehub.rest.shopee.filter.ShopeeShopApiClientRequestFilter;
import com.commercehub.rest.shopee.input.BoostItemInput;
import com.commercehub.rest.shopee.input.GetItemBaseInfoInput;
import com.commercehub.rest.shopee.input.GetItemListInput;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.BoostItemOutput;
import com.commercehub.rest.shopee.output.GetBoostedListOutput;
import com.commercehub.rest.shopee.output.GetItemBaseInfoOutput;
import com.commercehub.rest.shopee.output.GetItemListOutput;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RegisterRestClient(configKey = "shopee-api")
@RegisterProvider(ShopeeShopApiClientRequestFilter.class)
//@RegisterProvider(ShopeeClientResponseFilter.class)
public interface ShopeeProductService {

    @GET
    @Path("/api/v2/product/get_item_list")
    Uni<GetItemListOutput> getItemList(
            @BeanParam ShopApiCommonParam commonParam,
            @BeanParam GetItemListInput getItemListInput
    );

    @GET
    @Path("/api/v2/product/get_item_base_info")
    Uni<GetItemBaseInfoOutput> getItemBaseInfo(
            @BeanParam ShopApiCommonParam commonParam,
            @BeanParam GetItemBaseInfoInput getItemBaseInfoInput
    );

    @GET
    @Path("/api/v2/product/get_boosted_list")
    Uni<GetBoostedListOutput> getBoostedList(
            @BeanParam ShopApiCommonParam commonParam
    );

    @POST
    @Path("/api/v2/product/boost_item")
    Uni<BoostItemOutput> boostItem(
            @BeanParam ShopApiCommonParam commonParam,
            BoostItemInput boostItemInput
    );

}

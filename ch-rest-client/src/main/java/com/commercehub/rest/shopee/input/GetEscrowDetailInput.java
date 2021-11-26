package com.commercehub.rest.shopee.input;

import javax.ws.rs.QueryParam;

public class GetEscrowDetailInput {

    @QueryParam("order_sn")
    public final String order_sn;

    public GetEscrowDetailInput(String order_sn) {
        this.order_sn = order_sn;
    }

}

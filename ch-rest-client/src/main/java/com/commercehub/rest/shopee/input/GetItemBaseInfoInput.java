package com.commercehub.rest.shopee.input;

import javax.ws.rs.QueryParam;
import java.util.List;

public class GetItemBaseInfoInput {

    @QueryParam("item_id_list")
    public final List<Long> item_id_list;

    @QueryParam("need_tax_info")
    public final boolean need_tax_info;

    @QueryParam("need_complaint_policy")
    public final boolean need_complaint_policy;

    public GetItemBaseInfoInput(List<Long> item_id_list, boolean need_tax_info, boolean need_complaint_policy) {
        this.item_id_list = item_id_list;
        this.need_tax_info = need_tax_info;
        this.need_complaint_policy = need_complaint_policy;
    }

    @Override
    public String toString() {
        return "GetItemBaseInfoInput{" +
                "item_id_list=" + item_id_list +
                ", need_tax_info=" + need_tax_info +
                ", need_complaint_policy=" + need_complaint_policy +
                '}';
    }
}

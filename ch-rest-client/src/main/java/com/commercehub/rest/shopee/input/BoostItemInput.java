package com.commercehub.rest.shopee.input;

import java.util.List;

public class BoostItemInput {

    public final List<Long> item_id_list;

    public BoostItemInput(List<Long> item_id_list) {
        this.item_id_list = item_id_list;
    }

    @Override
    public String toString() {
        return "BoostItemInput{" +
                "item_id_list=" + item_id_list +
                '}';
    }
}

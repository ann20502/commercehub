package com.commercehub.rest.shopee.input;

import javax.ws.rs.QueryParam;

public class GetOrderDetailInput {

    @QueryParam("order_sn_list")
    public final String order_sn_list;

    @QueryParam("response_optional_fields")
    public final String response_optional_fields = String.join(
            ",",
            new String[] {
                    "buyer_user_id",
                    "buyer_username",
                    "estimated_shipping_fee",
                    "recipient_address",
                    "actual_shipping_fee",
                    "goods_to_declare",
                    "note",
                    "note_update_time",
                    "item_list",
                    "pay_time",
                    "dropshipper",
                    "dropshipper_phone",
                    "split_up",
                    "buyer_cancel_reason",
                    "cancel_by",
                    "cancel_reason",
                    "actual_shipping_fee_confirmed",
                    "fulfillment_flag",
                    "pickup_done_time",
                    "package_list",
                    "shipping_carrier",
                    "payment_method",
                    "total_amount",
                    "buyer_username",
                    "invoice_data",
                    "checkout_shipping_carrier",
                    "reverse_shipping_fee"
            }
    );

    public GetOrderDetailInput(String order_sn_list) {
        this.order_sn_list = order_sn_list;
    }

}

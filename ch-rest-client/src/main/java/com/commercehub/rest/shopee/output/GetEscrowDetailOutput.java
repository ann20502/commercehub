package com.commercehub.rest.shopee.output;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GetEscrowDetailOutput {

    private String request_id;
    private String error;
    private String message;
    private Response response;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "GetEscrowDetailOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }

    public static class Response {

        private String order_sn;
        private String buyer_user_name;
        private String[] return_order_sn_list;
        private OrderIncome order_income;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getBuyer_user_name() {
            return buyer_user_name;
        }

        public void setBuyer_user_name(String buyer_user_name) {
            this.buyer_user_name = buyer_user_name;
        }

        public String[] getReturn_order_sn_list() {
            return return_order_sn_list;
        }

        public void setReturn_order_sn_list(String[] return_order_sn_list) {
            this.return_order_sn_list = return_order_sn_list;
        }

        public OrderIncome getOrder_income() {
            return order_income;
        }

        public void setOrder_income(OrderIncome order_income) {
            this.order_income = order_income;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "order_sn='" + order_sn + '\'' +
                    ", buyer_user_name='" + buyer_user_name + '\'' +
                    ", return_order_sn_list=" + Arrays.toString(return_order_sn_list) +
                    ", order_income=" + order_income +
                    '}';
        }
    }

    public static class OrderIncome {

        private BigDecimal escrow_amount;
        private BigDecimal buyer_total_amount;
        private BigDecimal original_price;
        private BigDecimal seller_discount;
        private BigDecimal shopee_discount;
        private BigDecimal voucher_from_seller;
        private BigDecimal voucher_from_shopee;
        private BigDecimal coins;
        private BigDecimal buyer_paid_shipping_fee;
        private BigDecimal buyer_transaction_fee;
        private BigDecimal cross_border_tax;
        private BigDecimal payment_promotion;
        private BigDecimal commission_fee;
        private BigDecimal service_fee;
        private BigDecimal seller_transaction_fee;
        private BigDecimal seller_lost_compensation;
        private BigDecimal seller_coin_cash_back;
        private BigDecimal escrow_tax;
        private BigDecimal final_shipping_fee;
        private BigDecimal actual_shipping_fee;
        private BigDecimal shopee_shipping_rebate;
        private BigDecimal shipping_fee_discount_from_3pl;
        private BigDecimal seller_shipping_discount;
        private BigDecimal estimated_shipping_fee;
        private String[] seller_voucher_code;
        private BigDecimal drc_adjustable_refund;
        private BigDecimal cost_of_goods_sold;
        private BigDecimal original_cost_of_goods_sold;
        private BigDecimal original_shopee_discount;
        private BigDecimal seller_return_refund;
        private List<Item> items;
        private BigDecimal buyer_total_amount_pri;
        private BigDecimal original_price_pri;
        private BigDecimal seller_return_refund_pri;
        private BigDecimal commission_fee_pri;
        private BigDecimal service_fee_pri;
        private BigDecimal drc_adjustable_refund_pri;
        private String pri_currency;
        private String aff_currency;
        private BigDecimal exchange_rate;
        private BigDecimal reverse_shipping_fee;

        public BigDecimal getEscrow_amount() {
            return escrow_amount;
        }

        public void setEscrow_amount(BigDecimal escrow_amount) {
            this.escrow_amount = escrow_amount;
        }

        public BigDecimal getBuyer_total_amount() {
            return buyer_total_amount;
        }

        public void setBuyer_total_amount(BigDecimal buyer_total_amount) {
            this.buyer_total_amount = buyer_total_amount;
        }

        public BigDecimal getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(BigDecimal original_price) {
            this.original_price = original_price;
        }

        public BigDecimal getSeller_discount() {
            return seller_discount;
        }

        public void setSeller_discount(BigDecimal seller_discount) {
            this.seller_discount = seller_discount;
        }

        public BigDecimal getShopee_discount() {
            return shopee_discount;
        }

        public void setShopee_discount(BigDecimal shopee_discount) {
            this.shopee_discount = shopee_discount;
        }

        public BigDecimal getVoucher_from_seller() {
            return voucher_from_seller;
        }

        public void setVoucher_from_seller(BigDecimal voucher_from_seller) {
            this.voucher_from_seller = voucher_from_seller;
        }

        public BigDecimal getVoucher_from_shopee() {
            return voucher_from_shopee;
        }

        public void setVoucher_from_shopee(BigDecimal voucher_from_shopee) {
            this.voucher_from_shopee = voucher_from_shopee;
        }

        public BigDecimal getCoins() {
            return coins;
        }

        public void setCoins(BigDecimal coins) {
            this.coins = coins;
        }

        public BigDecimal getBuyer_paid_shipping_fee() {
            return buyer_paid_shipping_fee;
        }

        public void setBuyer_paid_shipping_fee(BigDecimal buyer_paid_shipping_fee) {
            this.buyer_paid_shipping_fee = buyer_paid_shipping_fee;
        }

        public BigDecimal getBuyer_transaction_fee() {
            return buyer_transaction_fee;
        }

        public void setBuyer_transaction_fee(BigDecimal buyer_transaction_fee) {
            this.buyer_transaction_fee = buyer_transaction_fee;
        }

        public BigDecimal getCross_border_tax() {
            return cross_border_tax;
        }

        public void setCross_border_tax(BigDecimal cross_border_tax) {
            this.cross_border_tax = cross_border_tax;
        }

        public BigDecimal getPayment_promotion() {
            return payment_promotion;
        }

        public void setPayment_promotion(BigDecimal payment_promotion) {
            this.payment_promotion = payment_promotion;
        }

        public BigDecimal getCommission_fee() {
            return commission_fee;
        }

        public void setCommission_fee(BigDecimal commission_fee) {
            this.commission_fee = commission_fee;
        }

        public BigDecimal getService_fee() {
            return service_fee;
        }

        public void setService_fee(BigDecimal service_fee) {
            this.service_fee = service_fee;
        }

        public BigDecimal getSeller_transaction_fee() {
            return seller_transaction_fee;
        }

        public void setSeller_transaction_fee(BigDecimal seller_transaction_fee) {
            this.seller_transaction_fee = seller_transaction_fee;
        }

        public BigDecimal getSeller_lost_compensation() {
            return seller_lost_compensation;
        }

        public void setSeller_lost_compensation(BigDecimal seller_lost_compensation) {
            this.seller_lost_compensation = seller_lost_compensation;
        }

        public BigDecimal getSeller_coin_cash_back() {
            return seller_coin_cash_back;
        }

        public void setSeller_coin_cash_back(BigDecimal seller_coin_cash_back) {
            this.seller_coin_cash_back = seller_coin_cash_back;
        }

        public BigDecimal getEscrow_tax() {
            return escrow_tax;
        }

        public void setEscrow_tax(BigDecimal escrow_tax) {
            this.escrow_tax = escrow_tax;
        }

        public BigDecimal getFinal_shipping_fee() {
            return final_shipping_fee;
        }

        public void setFinal_shipping_fee(BigDecimal final_shipping_fee) {
            this.final_shipping_fee = final_shipping_fee;
        }

        public BigDecimal getActual_shipping_fee() {
            return actual_shipping_fee;
        }

        public void setActual_shipping_fee(BigDecimal actual_shipping_fee) {
            this.actual_shipping_fee = actual_shipping_fee;
        }

        public BigDecimal getShopee_shipping_rebate() {
            return shopee_shipping_rebate;
        }

        public void setShopee_shipping_rebate(BigDecimal shopee_shipping_rebate) {
            this.shopee_shipping_rebate = shopee_shipping_rebate;
        }

        public BigDecimal getShipping_fee_discount_from_3pl() {
            return shipping_fee_discount_from_3pl;
        }

        public void setShipping_fee_discount_from_3pl(BigDecimal shipping_fee_discount_from_3pl) {
            this.shipping_fee_discount_from_3pl = shipping_fee_discount_from_3pl;
        }

        public BigDecimal getSeller_shipping_discount() {
            return seller_shipping_discount;
        }

        public void setSeller_shipping_discount(BigDecimal seller_shipping_discount) {
            this.seller_shipping_discount = seller_shipping_discount;
        }

        public BigDecimal getEstimated_shipping_fee() {
            return estimated_shipping_fee;
        }

        public void setEstimated_shipping_fee(BigDecimal estimated_shipping_fee) {
            this.estimated_shipping_fee = estimated_shipping_fee;
        }

        public String[] getSeller_voucher_code() {
            return seller_voucher_code;
        }

        public void setSeller_voucher_code(String[] seller_voucher_code) {
            this.seller_voucher_code = seller_voucher_code;
        }

        public BigDecimal getDrc_adjustable_refund() {
            return drc_adjustable_refund;
        }

        public void setDrc_adjustable_refund(BigDecimal drc_adjustable_refund) {
            this.drc_adjustable_refund = drc_adjustable_refund;
        }

        public BigDecimal getCost_of_goods_sold() {
            return cost_of_goods_sold;
        }

        public void setCost_of_goods_sold(BigDecimal cost_of_goods_sold) {
            this.cost_of_goods_sold = cost_of_goods_sold;
        }

        public BigDecimal getOriginal_cost_of_goods_sold() {
            return original_cost_of_goods_sold;
        }

        public void setOriginal_cost_of_goods_sold(BigDecimal original_cost_of_goods_sold) {
            this.original_cost_of_goods_sold = original_cost_of_goods_sold;
        }

        public BigDecimal getOriginal_shopee_discount() {
            return original_shopee_discount;
        }

        public void setOriginal_shopee_discount(BigDecimal original_shopee_discount) {
            this.original_shopee_discount = original_shopee_discount;
        }

        public BigDecimal getSeller_return_refund() {
            return seller_return_refund;
        }

        public void setSeller_return_refund(BigDecimal seller_return_refund) {
            this.seller_return_refund = seller_return_refund;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public BigDecimal getBuyer_total_amount_pri() {
            return buyer_total_amount_pri;
        }

        public void setBuyer_total_amount_pri(BigDecimal buyer_total_amount_pri) {
            this.buyer_total_amount_pri = buyer_total_amount_pri;
        }

        public BigDecimal getOriginal_price_pri() {
            return original_price_pri;
        }

        public void setOriginal_price_pri(BigDecimal original_price_pri) {
            this.original_price_pri = original_price_pri;
        }

        public BigDecimal getSeller_return_refund_pri() {
            return seller_return_refund_pri;
        }

        public void setSeller_return_refund_pri(BigDecimal seller_return_refund_pri) {
            this.seller_return_refund_pri = seller_return_refund_pri;
        }

        public BigDecimal getCommission_fee_pri() {
            return commission_fee_pri;
        }

        public void setCommission_fee_pri(BigDecimal commission_fee_pri) {
            this.commission_fee_pri = commission_fee_pri;
        }

        public BigDecimal getService_fee_pri() {
            return service_fee_pri;
        }

        public void setService_fee_pri(BigDecimal service_fee_pri) {
            this.service_fee_pri = service_fee_pri;
        }

        public BigDecimal getDrc_adjustable_refund_pri() {
            return drc_adjustable_refund_pri;
        }

        public void setDrc_adjustable_refund_pri(BigDecimal drc_adjustable_refund_pri) {
            this.drc_adjustable_refund_pri = drc_adjustable_refund_pri;
        }

        public String getPri_currency() {
            return pri_currency;
        }

        public void setPri_currency(String pri_currency) {
            this.pri_currency = pri_currency;
        }

        public String getAff_currency() {
            return aff_currency;
        }

        public void setAff_currency(String aff_currency) {
            this.aff_currency = aff_currency;
        }

        public BigDecimal getExchange_rate() {
            return exchange_rate;
        }

        public void setExchange_rate(BigDecimal exchange_rate) {
            this.exchange_rate = exchange_rate;
        }

        public BigDecimal getReverse_shipping_fee() {
            return reverse_shipping_fee;
        }

        public void setReverse_shipping_fee(BigDecimal reverse_shipping_fee) {
            this.reverse_shipping_fee = reverse_shipping_fee;
        }

        @Override
        public String toString() {
            return "OrderIncome{" +
                    "escrow_amount=" + escrow_amount +
                    ", buyer_total_amount=" + buyer_total_amount +
                    ", original_price=" + original_price +
                    ", seller_discount=" + seller_discount +
                    ", shopee_discount=" + shopee_discount +
                    ", voucher_from_seller=" + voucher_from_seller +
                    ", voucher_from_shopee=" + voucher_from_shopee +
                    ", coins=" + coins +
                    ", buyer_paid_shipping_fee=" + buyer_paid_shipping_fee +
                    ", buyer_transaction_fee=" + buyer_transaction_fee +
                    ", cross_border_tax=" + cross_border_tax +
                    ", payment_promotion=" + payment_promotion +
                    ", commission_fee=" + commission_fee +
                    ", service_fee=" + service_fee +
                    ", seller_transaction_fee=" + seller_transaction_fee +
                    ", seller_lost_compensation=" + seller_lost_compensation +
                    ", seller_coin_cash_back=" + seller_coin_cash_back +
                    ", escrow_tax=" + escrow_tax +
                    ", final_shipping_fee=" + final_shipping_fee +
                    ", actual_shipping_fee=" + actual_shipping_fee +
                    ", shopee_shipping_rebate=" + shopee_shipping_rebate +
                    ", shipping_fee_discount_from_3pl=" + shipping_fee_discount_from_3pl +
                    ", seller_shipping_discount=" + seller_shipping_discount +
                    ", estimated_shipping_fee=" + estimated_shipping_fee +
                    ", seller_voucher_code=" + Arrays.toString(seller_voucher_code) +
                    ", drc_adjustable_refund=" + drc_adjustable_refund +
                    ", cost_of_goods_sold=" + cost_of_goods_sold +
                    ", original_cost_of_goods_sold=" + original_cost_of_goods_sold +
                    ", original_shopee_discount=" + original_shopee_discount +
                    ", seller_return_refund=" + seller_return_refund +
                    ", items=" + items +
                    ", buyer_total_amount_pri=" + buyer_total_amount_pri +
                    ", original_price_pri=" + original_price_pri +
                    ", seller_return_refund_pri=" + seller_return_refund_pri +
                    ", commission_fee_pri=" + commission_fee_pri +
                    ", service_fee_pri=" + service_fee_pri +
                    ", drc_adjustable_refund_pri=" + drc_adjustable_refund_pri +
                    ", pri_currency='" + pri_currency + '\'' +
                    ", aff_currency='" + aff_currency + '\'' +
                    ", exchange_rate=" + exchange_rate +
                    ", reverse_shipping_fee=" + reverse_shipping_fee +
                    '}';
        }
    }

    public static class Item {

        private long item_id;
        private String item_name;
        private String item_sku;
        private long model_id;
        private String model_name;
        private String model_sku;
        private BigDecimal original_price;
        private BigDecimal discounted_price;
        private BigDecimal discount_from_coin;
        private BigDecimal discount_from_voucher_shopee;
        private BigDecimal discount_from_voucher_seller;
        private String activity_type;
        private long activity_id;
        private boolean is_main_item;

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_sku() {
            return item_sku;
        }

        public void setItem_sku(String item_sku) {
            this.item_sku = item_sku;
        }

        public long getModel_id() {
            return model_id;
        }

        public void setModel_id(long model_id) {
            this.model_id = model_id;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public String getModel_sku() {
            return model_sku;
        }

        public void setModel_sku(String model_sku) {
            this.model_sku = model_sku;
        }

        public BigDecimal getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(BigDecimal original_price) {
            this.original_price = original_price;
        }

        public BigDecimal getDiscounted_price() {
            return discounted_price;
        }

        public void setDiscounted_price(BigDecimal discounted_price) {
            this.discounted_price = discounted_price;
        }

        public BigDecimal getDiscount_from_coin() {
            return discount_from_coin;
        }

        public void setDiscount_from_coin(BigDecimal discount_from_coin) {
            this.discount_from_coin = discount_from_coin;
        }

        public BigDecimal getDiscount_from_voucher_shopee() {
            return discount_from_voucher_shopee;
        }

        public void setDiscount_from_voucher_shopee(BigDecimal discount_from_voucher_shopee) {
            this.discount_from_voucher_shopee = discount_from_voucher_shopee;
        }

        public BigDecimal getDiscount_from_voucher_seller() {
            return discount_from_voucher_seller;
        }

        public void setDiscount_from_voucher_seller(BigDecimal discount_from_voucher_seller) {
            this.discount_from_voucher_seller = discount_from_voucher_seller;
        }

        public String getActivity_type() {
            return activity_type;
        }

        public void setActivity_type(String activity_type) {
            this.activity_type = activity_type;
        }

        public long getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(long activity_id) {
            this.activity_id = activity_id;
        }

        public boolean isIs_main_item() {
            return is_main_item;
        }

        public void setIs_main_item(boolean is_main_item) {
            this.is_main_item = is_main_item;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "item_id=" + item_id +
                    ", item_name='" + item_name + '\'' +
                    ", item_sku='" + item_sku + '\'' +
                    ", model_id=" + model_id +
                    ", model_name='" + model_name + '\'' +
                    ", model_sku='" + model_sku + '\'' +
                    ", original_price=" + original_price +
                    ", discounted_price=" + discounted_price +
                    ", discount_from_coin=" + discount_from_coin +
                    ", discount_from_voucher_shopee=" + discount_from_voucher_shopee +
                    ", discount_from_voucher_seller=" + discount_from_voucher_seller +
                    ", activity_type='" + activity_type + '\'' +
                    ", activity_id=" + activity_id +
                    ", is_main_item=" + is_main_item +
                    '}';
        }
    }

}

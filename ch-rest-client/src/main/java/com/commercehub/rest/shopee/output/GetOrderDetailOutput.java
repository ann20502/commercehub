package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrderDetailOutput {

    private String request_id;
    private String error;
    private String message;
    private Response response;
    private String[] warning;

    public String getRequest_id() {
        return request_id;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Response getResponse() {
        return response;
    }

    public String[] getWarning() {
        return warning;
    }

    @Override
    public String toString() {
        return "GetOrderDetailOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                ", warning=" + Arrays.toString(warning) +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        private List<Order> order_list;

        public List<Order> getOrder_list() {
            return order_list;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "order_list=" + order_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Order {

        private String order_sn;
        private String region;
        private String currency;
        private boolean cod;
        private BigDecimal total_amount;
        private String order_status;
        private String shipping_carrier;
        private String payment_method;
        private BigDecimal estimated_shipping_fee;
        private String message_to_seller;
        private long create_time;
        private long update_time;
        private int days_to_ship;
        private long ship_by_date;
        private long buyer_user_id;
        private String buyer_username;
        private RecipientAddress recipient_address;
        private BigDecimal actual_shipping_fee;
        private boolean goods_to_declare;
        private String note;
        private long note_update_time;
        private List<Item> item_list;
        private long pay_time;
        private String dropshipper;
        private String credit_card_number;
        private String dropshipper_phone;
        private boolean split_up;
        private String buyer_cancel_reason;
        private String cancel_by;
        private String cancel_reason;
        private boolean actual_shipping_fee_confirmed;
        private String buyer_cpf_id;
        private String fulfillment_flag;
        private long pickup_done_time;
        private List<Package> package_list;
        private Invoice invoice_data;
        private String checkout_shipping_carrier;
        private BigDecimal reverse_shipping_fee;

        public String getOrder_sn() {
            return order_sn;
        }

        public String getRegion() {
            return region;
        }

        public String getCurrency() {
            return currency;
        }

        public boolean isCod() {
            return cod;
        }

        public BigDecimal getTotal_amount() {
            return total_amount;
        }

        public String getOrder_status() {
            return order_status;
        }

        public String getShipping_carrier() {
            return shipping_carrier;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public BigDecimal getEstimated_shipping_fee() {
            return estimated_shipping_fee;
        }

        public String getMessage_to_seller() {
            return message_to_seller;
        }

        public long getCreate_time() {
            return create_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public int getDays_to_shipl() {
            return days_to_ship;
        }

        public long getShip_by_date() {
            return ship_by_date;
        }

        public long getBuyer_user_id() {
            return buyer_user_id;
        }

        public String getBuyer_username() {
            return buyer_username;
        }

        public RecipientAddress getRecipient_address() {
            return recipient_address;
        }

        public BigDecimal getActual_shipping_fee() {
            return actual_shipping_fee;
        }

        public boolean isGoods_to_declare() {
            return goods_to_declare;
        }

        public String getNote() {
            return note;
        }

        public long getNote_update_time() {
            return note_update_time;
        }

        public List<Item> getItem_list() {
            return item_list;
        }

        public long getPay_time() {
            return pay_time;
        }

        public String getDropshipper() {
            return dropshipper;
        }

        public String getCredit_card_number() {
            return credit_card_number;
        }

        public String getDropshipper_phone() {
            return dropshipper_phone;
        }

        public boolean isSplit_up() {
            return split_up;
        }

        public String getBuyer_cancel_reason() {
            return buyer_cancel_reason;
        }

        public String getCancel_by() {
            return cancel_by;
        }

        public String getCancel_reason() {
            return cancel_reason;
        }

        public boolean isActual_shipping_fee_confirmed() {
            return actual_shipping_fee_confirmed;
        }

        public String getBuyer_cpf_id() {
            return buyer_cpf_id;
        }

        public String getFulfillment_flag() {
            return fulfillment_flag;
        }

        public long getPickup_done_time() {
            return pickup_done_time;
        }

        public List<Package> getPackage_list() {
            return package_list;
        }

        public Invoice getInvoice_data() {
            return invoice_data;
        }

        public String getCheckout_shipping_carrier() {
            return checkout_shipping_carrier;
        }

        public BigDecimal getReverse_shipping_fee() {
            return reverse_shipping_fee;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "order_sn='" + order_sn + '\'' +
                    ", region='" + region + '\'' +
                    ", currency='" + currency + '\'' +
                    ", cod=" + cod +
                    ", total_amount=" + total_amount +
                    ", order_status='" + order_status + '\'' +
                    ", shipping_carrier='" + shipping_carrier + '\'' +
                    ", payment_method='" + payment_method + '\'' +
                    ", estimated_shipping_fee=" + estimated_shipping_fee +
                    ", message_to_seller='" + message_to_seller + '\'' +
                    ", create_time=" + create_time +
                    ", update_time=" + update_time +
                    ", days_to_ship=" + days_to_ship +
                    ", ship_by_date=" + ship_by_date +
                    ", buyer_user_id=" + buyer_user_id +
                    ", buyer_username='" + buyer_username + '\'' +
                    ", recipient_address=" + recipient_address +
                    ", actual_shipping_fee=" + actual_shipping_fee +
                    ", goods_to_declare=" + goods_to_declare +
                    ", note='" + note + '\'' +
                    ", note_update_time=" + note_update_time +
                    ", item_list=" + item_list +
                    ", pay_time=" + pay_time +
                    ", dropshipper='" + dropshipper + '\'' +
                    ", credit_card_number='" + credit_card_number + '\'' +
                    ", dropshipper_phone='" + dropshipper_phone + '\'' +
                    ", split_up=" + split_up +
                    ", buyer_cancel_reason='" + buyer_cancel_reason + '\'' +
                    ", cancel_by='" + cancel_by + '\'' +
                    ", cancel_reason='" + cancel_reason + '\'' +
                    ", actual_shipping_fee_confirmed=" + actual_shipping_fee_confirmed +
                    ", buyer_cpf_id='" + buyer_cpf_id + '\'' +
                    ", fulfillment_flag='" + fulfillment_flag + '\'' +
                    ", pickup_done_time=" + pickup_done_time +
                    ", package_list=" + package_list +
                    ", invoice_data=" + invoice_data +
                    ", checkout_shipping_carrier='" + checkout_shipping_carrier + '\'' +
                    ", reverse_shipping_fee=" + reverse_shipping_fee +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RecipientAddress {

        private String name;
        private String phone;
        private String town;
        private String district;
        private String city;
        private String state;
        private String region;
        private String zipcode;
        private String full_address;

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getTown() {
            return town;
        }

        public String getDistrict() {
            return district;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getRegion() {
            return region;
        }

        public String getZipcode() {
            return zipcode;
        }

        public String getFull_address() {
            return full_address;
        }

        @Override
        public String toString() {
            return "RecipientAddress{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", town='" + town + '\'' +
                    ", district='" + district + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", region='" + region + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", full_address='" + full_address + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        private long item_id;
        private String item_name;
        private String item_sku;
        private long model_id;
        private String model_name;
        private String model_sku;
        private int model_quantity_purchased;
        private BigDecimal modeL_original_price;
        private BigDecimal model_discounted_price;
        private boolean wholesale;
        private BigDecimal weight;
        private boolean add_on_deal;
        private boolean main_item;
        private long add_on_deal_id;
        private String promotion_type;
        private long promotion_id;
        private long order_item_id;
        private long promotion_group_id;
        private ImageInfo image_info;

        public long getItem_id() {
            return item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public String getItem_sku() {
            return item_sku;
        }

        public long getModel_id() {
            return model_id;
        }

        public String getModel_name() {
            return model_name;
        }

        public String getModel_sku() {
            return model_sku;
        }

        public int getModel_quantity_purchased() {
            return model_quantity_purchased;
        }

        public BigDecimal getModeL_original_price() {
            return modeL_original_price;
        }

        public BigDecimal getModel_discounted_price() {
            return model_discounted_price;
        }

        public boolean isWholesale() {
            return wholesale;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public boolean isAdd_on_deal() {
            return add_on_deal;
        }

        public boolean isMain_item() {
            return main_item;
        }

        public long getAdd_on_deal_id() {
            return add_on_deal_id;
        }

        public String getPromotion_type() {
            return promotion_type;
        }

        public long getPromotion_id() {
            return promotion_id;
        }

        public long getOrder_item_id() {
            return order_item_id;
        }

        public long getPromotion_group_id() {
            return promotion_group_id;
        }

        public ImageInfo getImage_info() {
            return image_info;
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
                    ", model_quantity_purchased=" + model_quantity_purchased +
                    ", modeL_original_price=" + modeL_original_price +
                    ", model_discounted_price=" + model_discounted_price +
                    ", wholesale=" + wholesale +
                    ", weight=" + weight +
                    ", add_on_deal=" + add_on_deal +
                    ", main_item=" + main_item +
                    ", add_on_deal_id=" + add_on_deal_id +
                    ", promotion_type='" + promotion_type + '\'' +
                    ", promotion_id=" + promotion_id +
                    ", order_item_id=" + order_item_id +
                    ", promotion_group_id=" + promotion_group_id +
                    ", image_info=" + image_info +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageInfo {

        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        @Override
        public String toString() {
            return "ImageInfo{" +
                    "image_url='" + image_url + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Package {
        private String package_number;
        private String logistics_status;
        private String shipping_carrier;
        private List<PackageItem> item_list;

        public String getPackage_number() {
            return package_number;
        }

        public String getLogistics_status() {
            return logistics_status;
        }

        public String getShipping_carrier() {
            return shipping_carrier;
        }

        public List<PackageItem> getItem_list() {
            return item_list;
        }

        @Override
        public String toString() {
            return "Package{" +
                    "package_number='" + package_number + '\'' +
                    ", logistics_status='" + logistics_status + '\'' +
                    ", shipping_carrier='" + shipping_carrier + '\'' +
                    ", item_list=" + item_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PackageItem {
        private long item_id;
        private long model_id;

        public long getItem_id() {
            return item_id;
        }

        public long getModel_id() {
            return model_id;
        }

        @Override
        public String toString() {
            return "PackageItem{" +
                    "item_id=" + item_id +
                    ", model_id=" + model_id +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Invoice {

        private String number;
        private String series_number;
        private String access_key;
        private long issue_date;
        private BigDecimal total_value;
        private BigDecimal products_total_value;
        private String tax_code;

        public String getNumber() {
            return number;
        }

        public String getSeries_number() {
            return series_number;
        }

        public String getAccess_key() {
            return access_key;
        }

        public long getIssue_date() {
            return issue_date;
        }

        public BigDecimal getTotal_value() {
            return total_value;
        }

        public BigDecimal getProducts_total_value() {
            return products_total_value;
        }

        public String getTax_code() {
            return tax_code;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "number='" + number + '\'' +
                    ", series_number='" + series_number + '\'' +
                    ", access_key='" + access_key + '\'' +
                    ", issue_date=" + issue_date +
                    ", total_value=" + total_value +
                    ", products_total_value=" + products_total_value +
                    ", tax_code='" + tax_code + '\'' +
                    '}';
        }
    }

}

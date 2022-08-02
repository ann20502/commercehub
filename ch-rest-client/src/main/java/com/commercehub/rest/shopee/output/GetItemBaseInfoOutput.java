package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetItemBaseInfoOutput {

    private String request_id;
    private String error;
    private String message;
    private String warning;
    private Response response;

    public String getRequest_id() {
        return request_id;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getWarning() {
        return warning;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "GetItemBaseInfoOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", warning='" + warning + '\'' +
                ", response=" + response +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        private List<Item> item_list;

        private DescriptionInfo description_info;

        private String description_type;

        public List<Item> getItem_list() {
            return item_list;
        }

        public DescriptionInfo getDescription_info() {
            return description_info;
        }

        public String getDescription_type() {
            return description_type;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "item_list=" + item_list +
                    ", description_info=" + description_info +
                    ", description_type='" + description_type + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        private long item_id;

        private long category_id;

        private String item_name;

        private String description;

        private String item_sku;

        private Instant create_time;

        private Instant update_time;

        private List<Attribute> attribute_list;

        private List<PriceInfo> price_info;

        private List<StockInfo> stock_info;

        private Image image;

        private String weight;

        private Dimension dimension;

        private List<LogisticInfo> logistic_info;

        private PreOrder pre_order;

        private List<Wholesales> wholesales;

        private String condition;

        private String size_chart;

        private String item_status;

        private boolean has_model;

        private long promotion_id;

        private List<VideoInfo> video_info;

        private Brand brand;

        private long item_dangerous;

        private ComplaintPolicy complaint_policy;

        private TaxInfo tax_info;

        private StockInfoV2 stock_info_v2;

        public long getItem_id() {
            return item_id;
        }

        public long getCategory_id() {
            return category_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public String getDescription() {
            return description;
        }

        public String getItem_sku() {
            return item_sku;
        }

        public Instant getCreate_time() {
            return create_time;
        }

        public Instant getUpdate_time() {
            return update_time;
        }

        public List<Attribute> getAttribute_list() {
            return attribute_list;
        }

        public List<PriceInfo> getPrice_info() {
            return price_info;
        }

        public List<StockInfo> getStock_info() {
            return stock_info;
        }

        public Image getImage() {
            return image;
        }

        public String getWeight() {
            return weight;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public List<LogisticInfo> getLogistic_info() {
            return logistic_info;
        }

        public PreOrder getPre_order() {
            return pre_order;
        }

        public List<Wholesales> getWholesales() {
            return wholesales;
        }

        public String getCondition() {
            return condition;
        }

        public String getSize_chart() {
            return size_chart;
        }

        public String getItem_status() {
            return item_status;
        }

        public boolean isHas_model() {
            return has_model;
        }

        public long getPromotion_id() {
            return promotion_id;
        }

        public List<VideoInfo> getVideo_info() {
            return video_info;
        }

        public Brand getBrand() {
            return brand;
        }

        public long getItem_dangerous() {
            return item_dangerous;
        }

        public ComplaintPolicy getComplaint_policy() {
            return complaint_policy;
        }

        public TaxInfo getTax_info() {
            return tax_info;
        }

        public StockInfoV2 getStock_info_v2() {
            return stock_info_v2;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "item_id=" + item_id +
                    ", category_id=" + category_id +
                    ", item_name='" + item_name + '\'' +
                    ", description='" + description + '\'' +
                    ", item_sku='" + item_sku + '\'' +
                    ", create_time=" + create_time +
                    ", update_time=" + update_time +
                    ", attribute_list=" + attribute_list +
                    ", price_info=" + price_info +
                    ", stock_info=" + stock_info +
                    ", image=" + image +
                    ", weight='" + weight + '\'' +
                    ", dimension=" + dimension +
                    ", logistic_info=" + logistic_info +
                    ", pre_order=" + pre_order +
                    ", wholesales=" + wholesales +
                    ", condition='" + condition + '\'' +
                    ", size_chart='" + size_chart + '\'' +
                    ", item_status='" + item_status + '\'' +
                    ", has_model=" + has_model +
                    ", promotion_id=" + promotion_id +
                    ", video_info=" + video_info +
                    ", brand=" + brand +
                    ", item_dangerous=" + item_dangerous +
                    ", complaint_policy=" + complaint_policy +
                    ", tax_info=" + tax_info +
                    ", stock_info_v2=" + stock_info_v2 +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attribute {

        private long attribute_id;

        private String original_attribute_name;

        private boolean is_mandatory;

        private List<AttributeValue> attribute_value_list;

        public long getAttribute_id() {
            return attribute_id;
        }

        public String getOriginal_attribute_name() {
            return original_attribute_name;
        }

        public boolean isIs_mandatory() {
            return is_mandatory;
        }

        public List<AttributeValue> getAttribute_value_list() {
            return attribute_value_list;
        }

        @Override
        public String toString() {
            return "Attribute{" +
                    "attribute_id=" + attribute_id +
                    ", original_attribute_name='" + original_attribute_name + '\'' +
                    ", is_mandatory=" + is_mandatory +
                    ", attribute_value_list=" + attribute_value_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AttributeValue {

        private long value_id;

        private String original_value_name;

        private String value_unit;

        public long getValue_id() {
            return value_id;
        }

        public String getOriginal_value_name() {
            return original_value_name;
        }

        public String getValue_unit() {
            return value_unit;
        }

        @Override
        public String toString() {
            return "AttributeValue{" +
                    "value_id=" + value_id +
                    ", original_value_name='" + original_value_name + '\'' +
                    ", value_unit='" + value_unit + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PriceInfo {

        private String currency;
        private BigDecimal original_price;
        private BigDecimal current_price;
        private BigDecimal inflated_price_of_original_price;
        private BigDecimal inflated_price_of_current_price;
        private BigDecimal sip_item_price;
        private String sip_item_price_source;

        public String getCurrency() {
            return currency;
        }

        public BigDecimal getOriginal_price() {
            return original_price;
        }

        public BigDecimal getCurrent_price() {
            return current_price;
        }

        public BigDecimal getInflated_price_of_original_price() {
            return inflated_price_of_original_price;
        }

        public BigDecimal getInflated_price_of_current_price() {
            return inflated_price_of_current_price;
        }

        public BigDecimal getSip_item_price() {
            return sip_item_price;
        }

        public String getSip_item_price_source() {
            return sip_item_price_source;
        }

        @Override
        public String toString() {
            return "PriceInfo{" +
                    "currency='" + currency + '\'' +
                    ", original_price=" + original_price +
                    ", current_price=" + current_price +
                    ", inflated_price_of_original_price=" + inflated_price_of_original_price +
                    ", inflated_price_of_current_price=" + inflated_price_of_current_price +
                    ", sip_item_price=" + sip_item_price +
                    ", sip_item_price_source='" + sip_item_price_source + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StockInfo {

        private long stock_type;
        private String stock_location_id;
        private long current_stock;
        private long normal_stock;
        private long reserved_stock;

        public long getStock_type() {
            return stock_type;
        }

        public String getStock_location_id() {
            return stock_location_id;
        }

        public long getCurrent_stock() {
            return current_stock;
        }

        public long getNormal_stock() {
            return normal_stock;
        }

        public long getReserved_stock() {
            return reserved_stock;
        }

        @Override
        public String toString() {
            return "StockInfo{" +
                    "stock_type=" + stock_type +
                    ", stock_location_id='" + stock_location_id + '\'' +
                    ", current_stock=" + current_stock +
                    ", normal_stock=" + normal_stock +
                    ", reserved_stock=" + reserved_stock +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {

        private String[] image_url_list;
        private String[] image_id_list;

        public String[] getImage_url_list() {
            return image_url_list;
        }

        public String[] getImage_id_list() {
            return image_id_list;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "image_url_list=" + Arrays.toString(image_url_list) +
                    ", image_id_list=" + Arrays.toString(image_id_list) +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dimension {

        private long package_length;
        private long package_width;
        private long package_height;

        public long getPackage_length() {
            return package_length;
        }

        public long getPackage_width() {
            return package_width;
        }

        public long getPackage_height() {
            return package_height;
        }

        @Override
        public String toString() {
            return "Dimension{" +
                    "package_length=" + package_length +
                    ", package_width=" + package_width +
                    ", package_height=" + package_height +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LogisticInfo {

        private long logistic_id;
        private String logistic_name;
        private boolean enabled;
        private BigDecimal shipping_fee;
        private long size_id;
        private boolean is_free;
        private BigDecimal estimated_shipping_fee;

        public long getLogistic_id() {
            return logistic_id;
        }

        public String getLogistic_name() {
            return logistic_name;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public BigDecimal getShipping_fee() {
            return shipping_fee;
        }

        public long getSize_id() {
            return size_id;
        }

        public boolean isIs_free() {
            return is_free;
        }

        public BigDecimal getEstimated_shipping_fee() {
            return estimated_shipping_fee;
        }

        @Override
        public String toString() {
            return "LogisticInfo{" +
                    "logistic_id=" + logistic_id +
                    ", logistic_name='" + logistic_name + '\'' +
                    ", enabled=" + enabled +
                    ", shipping_fee=" + shipping_fee +
                    ", size_id=" + size_id +
                    ", is_free=" + is_free +
                    ", estimated_shipping_fee=" + estimated_shipping_fee +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PreOrder {

        private boolean is_pre_order;
        private long days_to_ship;

        public boolean isIs_pre_order() {
            return is_pre_order;
        }

        public long getDays_to_ship() {
            return days_to_ship;
        }

        @Override
        public String toString() {
            return "PreOrder{" +
                    "is_pre_order=" + is_pre_order +
                    ", days_to_ship=" + days_to_ship +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wholesales {

        private long min_count;
        private long max_count;
        private BigDecimal unit_price;
        private BigDecimal inflated_price_of_unit_price;

        public long getMin_count() {
            return min_count;
        }

        public long getMax_count() {
            return max_count;
        }

        public BigDecimal getUnit_price() {
            return unit_price;
        }

        public BigDecimal getInflated_price_of_unit_price() {
            return inflated_price_of_unit_price;
        }

        @Override
        public String toString() {
            return "Wholesales{" +
                    "min_count=" + min_count +
                    ", max_count=" + max_count +
                    ", unit_price=" + unit_price +
                    ", inflated_price_of_unit_price=" + inflated_price_of_unit_price +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VideoInfo {

        private String video_url;
        private String thumbnail_url;
        private long duration;

        public String getVideo_url() {
            return video_url;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public long getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "video_url='" + video_url + '\'' +
                    ", thumbnail_url='" + thumbnail_url + '\'' +
                    ", duration=" + duration +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Brand {

        private long brand_id;
        private String original_brand_name;

        public long getBrand_id() {
            return brand_id;
        }

        public String getOriginal_brand_name() {
            return original_brand_name;
        }

        @Override
        public String toString() {
            return "Brand{" +
                    "brand_id=" + brand_id +
                    ", original_brand_name='" + original_brand_name + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ComplaintPolicy {

        private String warranty_time;
        private boolean exclude_entrepreneur_warranty;
        private long complaint_address_id;
        private String additional_information;

        public String getWarranty_time() {
            return warranty_time;
        }

        public boolean isExclude_entrepreneur_warranty() {
            return exclude_entrepreneur_warranty;
        }

        public long getComplaint_address_id() {
            return complaint_address_id;
        }

        public String getAdditional_information() {
            return additional_information;
        }

        @Override
        public String toString() {
            return "ComplaintPolicy{" +
                    "warranty_time='" + warranty_time + '\'' +
                    ", exclude_entrepreneur_warranty=" + exclude_entrepreneur_warranty +
                    ", complaint_address_id=" + complaint_address_id +
                    ", additional_information='" + additional_information + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TaxInfo {

        private String ncm;
        private String diff_state_cfop;
        private String csosn;
        private String origin;
        private String cest;
        private String measure_unit;
        private String invoice_option;
        private String vat_rate;
        private String hs_code;
        private String tax_code;

        public String getNcm() {
            return ncm;
        }

        public String getDiff_state_cfop() {
            return diff_state_cfop;
        }

        public String getCsosn() {
            return csosn;
        }

        public String getOrigin() {
            return origin;
        }

        public String getCest() {
            return cest;
        }

        public String getMeasure_unit() {
            return measure_unit;
        }

        public String getInvoice_option() {
            return invoice_option;
        }

        public String getVat_rate() {
            return vat_rate;
        }

        public String getHs_code() {
            return hs_code;
        }

        public String getTax_code() {
            return tax_code;
        }

        @Override
        public String toString() {
            return "TaxInfo{" +
                    "ncm='" + ncm + '\'' +
                    ", diff_state_cfop='" + diff_state_cfop + '\'' +
                    ", csosn='" + csosn + '\'' +
                    ", origin='" + origin + '\'' +
                    ", cest='" + cest + '\'' +
                    ", measure_unit='" + measure_unit + '\'' +
                    ", invoice_option='" + invoice_option + '\'' +
                    ", vat_rate='" + vat_rate + '\'' +
                    ", hs_code='" + hs_code + '\'' +
                    ", tax_code='" + tax_code + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StockInfoV2 {

        private SummaryInfo summary_info;
        private SellerStock seller_stock;
        private ShopeeStock shopee_stock;

        public SummaryInfo getSummary_info() {
            return summary_info;
        }

        public SellerStock getSeller_stock() {
            return seller_stock;
        }

        public ShopeeStock getShopee_stock() {
            return shopee_stock;
        }

        @Override
        public String toString() {
            return "StockInfoV2{" +
                    "summary_info=" + summary_info +
                    ", seller_stock=" + seller_stock +
                    ", shopee_stock=" + shopee_stock +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SummaryInfo {

        private long total_reserved_stock;
        private long total_available_stock;

        public long getTotal_reserved_stock() {
            return total_reserved_stock;
        }

        public long getTotal_available_stock() {
            return total_available_stock;
        }

        @Override
        public String toString() {
            return "SummaryInfo{" +
                    "total_reserved_stock=" + total_reserved_stock +
                    ", total_available_stock=" + total_available_stock +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SellerStock {

        private String location_id;
        private long stock;

        public String getLocation_id() {
            return location_id;
        }

        public long getStock() {
            return stock;
        }

        @Override
        public String toString() {
            return "SellerStock{" +
                    "location_id='" + location_id + '\'' +
                    ", stock=" + stock +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ShopeeStock {

        private String location_id;
        private long stock;

        public String getLocation_id() {
            return location_id;
        }

        public long getStock() {
            return stock;
        }

        @Override
        public String toString() {
            return "ShopeeStock{" +
                    "location_id='" + location_id + '\'' +
                    ", stock=" + stock +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DescriptionInfo {

        private ExtendedDescription extended_description;

        public ExtendedDescription getExtended_description() {
            return extended_description;
        }

        @Override
        public String toString() {
            return "DescriptionInfo{" +
                    "extended_description=" + extended_description +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtendedDescription {

        private List<Field> field_list;

        public List<Field> getField_list() {
            return field_list;
        }

        @Override
        public String toString() {
            return "ExtendedDescription{" +
                    "field_list=" + field_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Field {

        private String field_type;
        private String text;
        private ImageInfo image_info;

        public String getField_type() {
            return field_type;
        }

        public String getText() {
            return text;
        }

        public ImageInfo getImage_info() {
            return image_info;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "field_type='" + field_type + '\'' +
                    ", text='" + text + '\'' +
                    ", image_info=" + image_info +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImageInfo {

        private String image_id;
        private String image_url;

        public String getImage_id() {
            return image_id;
        }

        public String getImage_url() {
            return image_url;
        }

        @Override
        public String toString() {
            return "ImageInfo{" +
                    "image_id='" + image_id + '\'' +
                    ", image_url='" + image_url + '\'' +
                    '}';
        }
    }

}

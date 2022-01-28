package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetShopPerformanceOutput {

    private String request_id;
    private String error;
    private String message;
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

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "GetShopPerformanceOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }

    public static class Response {
        private int overall_performance;
        private ListingViolation listing_violations;
        private Fulfillment fulfillment;
        private CustomerService customer_service;
        private CustomerSatisfaction customer_satisfaction;

        public int getOverall_performance() {
            return overall_performance;
        }

        public ListingViolation getListing_violations() {
            return listing_violations;
        }

        public Fulfillment getFulfillment() {
            return fulfillment;
        }

        public CustomerService getCustomer_service() {
            return customer_service;
        }

        public CustomerSatisfaction getCustomer_satisfaction() {
            return customer_satisfaction;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "overall_performance=" + overall_performance +
                    ", listing_violations=" + listing_violations +
                    ", fulfillment=" + fulfillment +
                    ", customer_service=" + customer_service +
                    ", customer_satisfaction=" + customer_satisfaction +
                    '}';
        }
    }

    public static class ListingViolation {

        private SevereListingViolation severe_listing_violations;
        private PreOrderListing pre_order_listing;
        private OtherListingViolation other_listing_violations;

        public SevereListingViolation getSevere_listing_violations() {
            return severe_listing_violations;
        }

        public PreOrderListing getPre_order_listing() {
            return pre_order_listing;
        }

        public OtherListingViolation getOther_listing_violations() {
            return other_listing_violations;
        }

        @Override
        public String toString() {
            return "ListingViolation{" +
                    "severe_listing_violations=" + severe_listing_violations +
                    ", pre_order_listing=" + pre_order_listing +
                    ", other_listing_violations=" + other_listing_violations +
                    '}';
        }
    }

    public static class SevereListingViolation {

        private Data total_data;
        private Data spam_listing_data;
        private Data counterfeit_ip_infringement_data;
        private Data prohibited_listing_data;

        public Data getTotal_data() {
            return total_data;
        }

        public Data getSpam_listing_data() {
            return spam_listing_data;
        }

        public Data getCounterfeit_ip_infringement_data() {
            return counterfeit_ip_infringement_data;
        }

        public Data getProhibited_listing_data() {
            return prohibited_listing_data;
        }

        @Override
        public String toString() {
            return "SevereListingViolation{" +
                    "total_data=" + total_data +
                    ", spam_listing_data=" + spam_listing_data +
                    ", counterfeit_ip_infringement_data=" + counterfeit_ip_infringement_data +
                    ", prohibited_listing_data=" + prohibited_listing_data +
                    '}';
        }
    }

    public static class PreOrderListing {

        private Data percent_pre_order_listing_data;
        private Data number_of_days_pre_order_listing_exceeds_target_data;

        public Data getPercent_pre_order_listing_data() {
            return percent_pre_order_listing_data;
        }

        public Data getNumber_of_days_pre_order_listing_exceeds_target_data() {
            return number_of_days_pre_order_listing_exceeds_target_data;
        }

        @Override
        public String toString() {
            return "PreOrderListing{" +
                    "percent_pre_order_listing_data=" + percent_pre_order_listing_data +
                    ", number_of_days_pre_order_listing_exceeds_target_data=" + number_of_days_pre_order_listing_exceeds_target_data +
                    '}';
        }
    }

    public static class OtherListingViolation {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "OtherListingViolation{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class Fulfillment {

        private NonFulfillmentRate non_fulfillment_rate;
        private PreparationTime preparation_time;
        private LateShipmentRate late_shipment_rate;

        public NonFulfillmentRate getNon_fulfillment_rate() {
            return non_fulfillment_rate;
        }

        public PreparationTime getPreparation_time() {
            return preparation_time;
        }

        public LateShipmentRate getLate_shipment_rate() {
            return late_shipment_rate;
        }

        @Override
        public String toString() {
            return "Fulfillment{" +
                    "non_fulfillment_rate=" + non_fulfillment_rate +
                    ", preparation_time=" + preparation_time +
                    ", late_shipment_rate=" + late_shipment_rate +
                    '}';
        }
    }

    public static class NonFulfillmentRate {

        private Data total_data;
        private Data cancellation_rate_data;
        private Data return_refund_rate_data;

        public Data getTotal_data() {
            return total_data;
        }

        public Data getCancellation_rate_data() {
            return cancellation_rate_data;
        }

        public Data getReturn_refund_rate_data() {
            return return_refund_rate_data;
        }

        @Override
        public String toString() {
            return "NonFulfillmentRate{" +
                    "total_data=" + total_data +
                    ", cancellation_rate_data=" + cancellation_rate_data +
                    ", return_refund_rate_data=" + return_refund_rate_data +
                    '}';
        }
    }

    public static class PreparationTime {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "PreparationTime{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class LateShipmentRate {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "LateShipmentRate{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class CustomerService {

        private ResponseRate response_rate;
        private ResponseTime response_time;

        public ResponseRate getResponse_rate() {
            return response_rate;
        }

        public ResponseTime getResponse_time() {
            return response_time;
        }

        @Override
        public String toString() {
            return "CustomerService{" +
                    "response_rate=" + response_rate +
                    ", response_time=" + response_time +
                    '}';
        }
    }

    public static class ResponseRate {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "ResponseRate{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class ResponseTime {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "ResponseTime{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class CustomerSatisfaction {

        private OverallReviewingRate overall_reviewing_rate;

        public OverallReviewingRate getOverall_reviewing_rate() {
            return overall_reviewing_rate;
        }

        @Override
        public String toString() {
            return "CustomerSatisfaction{" +
                    "overall_reviewing_rate=" + overall_reviewing_rate +
                    '}';
        }
    }

    public static class OverallReviewingRate {

        private Data total_data;

        public Data getTotal_data() {
            return total_data;
        }

        @Override
        public String toString() {
            return "OverallReviewingRate{" +
                    "total_data=" + total_data +
                    '}';
        }
    }

    public static class Data {

        private String target;
        private String my_shop_performance;
        private String penalty_points;

        public String getTarget() {
            return target;
        }

        public String getMy_shop_performance() {
            return my_shop_performance;
        }

        public String getPenalty_points() {
            return penalty_points;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "target='" + target + '\'' +
                    ", my_shop_performance='" + my_shop_performance + '\'' +
                    ", penalty_points='" + penalty_points + '\'' +
                    '}';
        }
    }

}

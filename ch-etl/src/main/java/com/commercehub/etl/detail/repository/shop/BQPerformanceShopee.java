package com.commercehub.etl.detail.repository.shop;

import com.squareup.moshi.Json;

import java.time.Instant;

public class BQPerformanceShopee {

    @Json(name = "extract_time")
    public final Instant extractTime;

    @Json(name = "overall_performance")
    public final int overallPerformance;

    @Json(name = "listing_overall")
    public final Data listingOverall;

    @Json(name = "listing_spam")
    public final Data listingSpam;

    @Json(name = "listing_counterfeit")
    public final Data listingCounterfeit;

    @Json(name = "listing_prohibited")
    public final Data listingProhibited;

    @Json(name = "listing_pre_order_percent")
    public final Data listingPreOrderPercent;

    @Json(name = "listing_pre_order_exceed_target")
    public final Data listingPreOrderExceedTarget;

    @Json(name = "listing_other")
    public final Data listingOther;

    @Json(name = "non_fulfillment_overall")
    public final Data nonFulfillmentOverall;

    @Json(name = "non_fulfillment_cancellation")
    public final Data nonFulfillmentCancellation;

    @Json(name = "non_fulfillment_return_refund")
    public final Data nonFulfillmentReturnRefund;

    @Json(name = "fulfillment_preparation_time")
    public final Data fulfillmentPreparationTime;

    @Json(name = "fulfillment_late_shipment")
    public final Data fulfillmentLateShipment;

    @Json(name = "cservice_response_overall")
    public final Data custServiceResponseOverall;

    @Json(name = "cservice_response_time")
    public final Data custServiceResponseTime;

    @Json(name = "csatisfaction_rating_overall")
    public final Data custSatisfactionRatingOverall;

    public BQPerformanceShopee(Instant extractTime, int overallPerformance, Data listingOverall, Data listingSpam, Data listingCounterfeit, Data listingProhibited, Data listingPreOrderPercent, Data listingPreOrderExceedTarget, Data listingOther, Data nonFulfillmentOverall, Data nonFulfillmentCancellation, Data nonFulfillmentReturnRefund, Data fulfillmentPreparationTime, Data fulfillmentLateShipment, Data custServiceResponseOverall, Data custServiceResponseTime, Data custSatisfactionRatingOverall) {
        this.extractTime = extractTime;
        this.overallPerformance = overallPerformance;
        this.listingOverall = listingOverall;
        this.listingSpam = listingSpam;
        this.listingCounterfeit = listingCounterfeit;
        this.listingProhibited = listingProhibited;
        this.listingPreOrderPercent = listingPreOrderPercent;
        this.listingPreOrderExceedTarget = listingPreOrderExceedTarget;
        this.listingOther = listingOther;
        this.nonFulfillmentOverall = nonFulfillmentOverall;
        this.nonFulfillmentCancellation = nonFulfillmentCancellation;
        this.nonFulfillmentReturnRefund = nonFulfillmentReturnRefund;
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        this.fulfillmentLateShipment = fulfillmentLateShipment;
        this.custServiceResponseOverall = custServiceResponseOverall;
        this.custServiceResponseTime = custServiceResponseTime;
        this.custSatisfactionRatingOverall = custSatisfactionRatingOverall;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "extractTime=" + extractTime +
                ", overallPerformance=" + overallPerformance +
                ", listingOverall=" + listingOverall +
                ", listingSpam=" + listingSpam +
                ", listingCounterfeit=" + listingCounterfeit +
                ", listingProhibited=" + listingProhibited +
                ", listingPreOrderPercent=" + listingPreOrderPercent +
                ", listingPreOrderExceedTarget=" + listingPreOrderExceedTarget +
                ", listingOther=" + listingOther +
                ", nonFulfillmentOverall=" + nonFulfillmentOverall +
                ", nonFulfillmentCancellation=" + nonFulfillmentCancellation +
                ", nonFulfillmentReturnRefund=" + nonFulfillmentReturnRefund +
                ", fulfillmentPreparationTime=" + fulfillmentPreparationTime +
                ", fulfillmentLateShipment=" + fulfillmentLateShipment +
                ", custServiceResponseOverall=" + custServiceResponseOverall +
                ", custServiceResponseTime=" + custServiceResponseTime +
                ", custSatisfactionRatingOverall=" + custSatisfactionRatingOverall +
                '}';
    }

    public static class Data {

        public final String target;
        public final String performance;

        @Json(name = "penalty_point")
        public final String penaltyPoint;

        public Data(String target, String performance, String penaltyPoint) {
            this.target = target;
            this.performance = performance;
            this.penaltyPoint = penaltyPoint;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "target='" + target + '\'' +
                    ", performance='" + performance + '\'' +
                    ", penaltyPoint='" + penaltyPoint + '\'' +
                    '}';
        }
    }

}

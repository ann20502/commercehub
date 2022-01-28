package com.commercehub.etl.domain.entity.shop;

import com.squareup.moshi.Json;

import java.time.Instant;

public class Performance {

    @Json(name = "extract_time")
    private Instant extractTime;

    @Json(name = "overall_performance")
    private int overallPerformance;

    @Json(name = "listing_overall")
    private Data listingOverall;

    @Json(name = "listing_spam")
    private Data listingSpam;

    @Json(name = "listing_counterfeit")
    private Data listingCounterfeit;

    @Json(name = "listing_prohibited")
    private Data listingProhibited;

    @Json(name = "listing_pre_order_percent")
    private Data listingPreOrderPercent;

    @Json(name = "listing_pre_order_exceed_target")
    private Data listingPreOrderExceedTarget;

    @Json(name = "listing_other")
    private Data listingOther;

    @Json(name = "non_fulfillment_overall")
    private Data nonFulfillmentOverall;

    @Json(name = "non_fulfillment_cancellation")
    private Data nonFulfillmentCancellation;

    @Json(name = "non_fulfillment_return_refund")
    private Data nonFulfillmentReturnRefund;

    @Json(name = "fulfillment_preparation_time")
    private Data fulfillmentPreparationTime;

    @Json(name = "fulfillment_late_shipment")
    private Data fulfillmentLateShipment;

    @Json(name = "cservice_response_overall")
    private Data custServiceResponseOverall;

    @Json(name = "cservice_response_time")
    private Data custServiceResponseTime;

    @Json(name = "csatisfaction_rating_overall")
    private Data custSatisfactionRatingOverall;

    public Performance(Instant extractTime, int overallPerformance, Data listingOverall, Data listingSpam, Data listingCounterfeit, Data listingProhibited, Data listingPreOrderPercent, Data listingPreOrderExceedTarget, Data listingOther, Data nonFulfillmentOverall, Data nonFulfillmentCancellation, Data nonFulfillmentReturnRefund, Data fulfillmentPreparationTime, Data fulfillmentLateShipment, Data custServiceResponseOverall, Data custServiceResponseTime, Data custSatisfactionRatingOverall) {
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

    public Instant getExtractTime() {
        return extractTime;
    }

    public void setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
    }

    public int getOverallPerformance() {
        return overallPerformance;
    }

    public void setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
    }

    public Data getListingOverall() {
        return listingOverall;
    }

    public void setListingOverall(Data listingOverall) {
        this.listingOverall = listingOverall;
    }

    public Data getListingSpam() {
        return listingSpam;
    }

    public void setListingSpam(Data listingSpam) {
        this.listingSpam = listingSpam;
    }

    public Data getListingCounterfeit() {
        return listingCounterfeit;
    }

    public void setListingCounterfeit(Data listingCounterfeit) {
        this.listingCounterfeit = listingCounterfeit;
    }

    public Data getListingProhibited() {
        return listingProhibited;
    }

    public void setListingProhibited(Data listingProhibited) {
        this.listingProhibited = listingProhibited;
    }

    public Data getListingPreOrderPercent() {
        return listingPreOrderPercent;
    }

    public void setListingPreOrderPercent(Data listingPreOrderPercent) {
        this.listingPreOrderPercent = listingPreOrderPercent;
    }

    public Data getListingPreOrderExceedTarget() {
        return listingPreOrderExceedTarget;
    }

    public void setListingPreOrderExceedTarget(Data listingPreOrderExceedTarget) {
        this.listingPreOrderExceedTarget = listingPreOrderExceedTarget;
    }

    public Data getListingOther() {
        return listingOther;
    }

    public void setListingOther(Data listingOther) {
        this.listingOther = listingOther;
    }

    public Data getNonFulfillmentOverall() {
        return nonFulfillmentOverall;
    }

    public void setNonFulfillmentOverall(Data nonFulfillmentOverall) {
        this.nonFulfillmentOverall = nonFulfillmentOverall;
    }

    public Data getNonFulfillmentCancellation() {
        return nonFulfillmentCancellation;
    }

    public void setNonFulfillmentCancellation(Data nonFulfillmentCancellation) {
        this.nonFulfillmentCancellation = nonFulfillmentCancellation;
    }

    public Data getNonFulfillmentReturnRefund() {
        return nonFulfillmentReturnRefund;
    }

    public void setNonFulfillmentReturnRefund(Data nonFulfillmentReturnRefund) {
        this.nonFulfillmentReturnRefund = nonFulfillmentReturnRefund;
    }

    public Data getFulfillmentPreparationTime() {
        return fulfillmentPreparationTime;
    }

    public void setFulfillmentPreparationTime(Data fulfillmentPreparationTime) {
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
    }

    public Data getFulfillmentLateShipment() {
        return fulfillmentLateShipment;
    }

    public void setFulfillmentLateShipment(Data fulfillmentLateShipment) {
        this.fulfillmentLateShipment = fulfillmentLateShipment;
    }

    public Data getCustServiceResponseOverall() {
        return custServiceResponseOverall;
    }

    public void setCustServiceResponseOverall(Data custServiceResponseOverall) {
        this.custServiceResponseOverall = custServiceResponseOverall;
    }

    public Data getCustServiceResponseTime() {
        return custServiceResponseTime;
    }

    public void setCustServiceResponseTime(Data custServiceResponseTime) {
        this.custServiceResponseTime = custServiceResponseTime;
    }

    public Data getCustSatisfactionRatingOverall() {
        return custSatisfactionRatingOverall;
    }

    public void setCustSatisfactionRatingOverall(Data custSatisfactionRatingOverall) {
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

        private String target;
        private String performance;

        @Json(name = "penalty_point")
        private String penaltyPoint;

        public Data(String target, String performance, String penaltyPoint) {
            this.target = target;
            this.performance = performance;
            this.penaltyPoint = penaltyPoint;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getPerformance() {
            return performance;
        }

        public void setPerformance(String performance) {
            this.performance = performance;
        }

        public String getPenaltyPoint() {
            return penaltyPoint;
        }

        public void setPenaltyPoint(String penaltyPoint) {
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

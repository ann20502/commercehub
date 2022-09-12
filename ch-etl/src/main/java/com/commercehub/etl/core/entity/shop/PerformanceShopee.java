package com.commercehub.etl.core.entity.shop;

import java.time.Instant;

public class PerformanceShopee implements Performance {

    public final Instant extractTime;

    public final int overallPerformance;

    public final Data listingOverall;

    public final Data listingSpam;

    public final Data listingCounterfeit;

    public final Data listingProhibited;

    public final Data listingPreOrderPercent;

    public final Data listingPreOrderExceedTarget;

    public final Data listingOther;

    public final Data nonFulfillmentOverall;

    public final Data nonFulfillmentCancellation;

    public final Data nonFulfillmentReturnRefund;

    public final Data fulfillmentPreparationTime;

    public final Data fulfillmentLateShipment;

    public final Data custServiceResponseOverall;

    public final Data custServiceResponseTime;

    public final Data custSatisfactionRatingOverall;

    public PerformanceShopee(Instant extractTime, int overallPerformance, Data listingOverall, Data listingSpam, Data listingCounterfeit, Data listingProhibited, Data listingPreOrderPercent, Data listingPreOrderExceedTarget, Data listingOther, Data nonFulfillmentOverall, Data nonFulfillmentCancellation, Data nonFulfillmentReturnRefund, Data fulfillmentPreparationTime, Data fulfillmentLateShipment, Data custServiceResponseOverall, Data custServiceResponseTime, Data custSatisfactionRatingOverall) {
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

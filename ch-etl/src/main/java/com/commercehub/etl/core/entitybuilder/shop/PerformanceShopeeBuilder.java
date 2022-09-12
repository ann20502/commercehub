package com.commercehub.etl.core.entitybuilder.shop;

import com.commercehub.etl.core.entity.shop.PerformanceShopee;

import java.time.Instant;

public class PerformanceShopeeBuilder {
    private Instant extractTime;
    private int overallPerformance;
    private PerformanceShopee.Data listingOverall;
    private PerformanceShopee.Data listingSpam;
    private PerformanceShopee.Data listingCounterfeit;
    private PerformanceShopee.Data listingProhibited;
    private PerformanceShopee.Data listingPreOrderPercent;
    private PerformanceShopee.Data listingPreOrderExceedTarget;
    private PerformanceShopee.Data listingOther;
    private PerformanceShopee.Data nonFulfillmentOverall;
    private PerformanceShopee.Data nonFulfillmentCancellation;
    private PerformanceShopee.Data nonFulfillmentReturnRefund;
    private PerformanceShopee.Data fulfillmentPreparationTime;
    private PerformanceShopee.Data fulfillmentLateShipment;
    private PerformanceShopee.Data custServiceResponseOverall;
    private PerformanceShopee.Data custServiceResponseTime;
    private PerformanceShopee.Data custSatisfactionRatingOverall;

    public PerformanceShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public PerformanceShopeeBuilder setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
        return this;
    }

    public PerformanceShopeeBuilder setListingOverall(PerformanceShopee.Data listingOverall) {
        this.listingOverall = listingOverall;
        return this;
    }

    public PerformanceShopeeBuilder setListingSpam(PerformanceShopee.Data listingSpam) {
        this.listingSpam = listingSpam;
        return this;
    }

    public PerformanceShopeeBuilder setListingCounterfeit(PerformanceShopee.Data listingCounterfeit) {
        this.listingCounterfeit = listingCounterfeit;
        return this;
    }

    public PerformanceShopeeBuilder setListingProhibited(PerformanceShopee.Data listingProhibited) {
        this.listingProhibited = listingProhibited;
        return this;
    }

    public PerformanceShopeeBuilder setListingPreOrderPercent(PerformanceShopee.Data listingPreOrderPercent) {
        this.listingPreOrderPercent = listingPreOrderPercent;
        return this;
    }

    public PerformanceShopeeBuilder setListingPreOrderExceedTarget(PerformanceShopee.Data listingPreOrderExceedTarget) {
        this.listingPreOrderExceedTarget = listingPreOrderExceedTarget;
        return this;
    }

    public PerformanceShopeeBuilder setListingOther(PerformanceShopee.Data listingOther) {
        this.listingOther = listingOther;
        return this;
    }

    public PerformanceShopeeBuilder setNonFulfillmentOverall(PerformanceShopee.Data nonFulfillmentOverall) {
        this.nonFulfillmentOverall = nonFulfillmentOverall;
        return this;
    }

    public PerformanceShopeeBuilder setNonFulfillmentCancellation(PerformanceShopee.Data nonFulfillmentCancellation) {
        this.nonFulfillmentCancellation = nonFulfillmentCancellation;
        return this;
    }

    public PerformanceShopeeBuilder setNonFulfillmentReturnRefund(PerformanceShopee.Data nonFulfillmentReturnRefund) {
        this.nonFulfillmentReturnRefund = nonFulfillmentReturnRefund;
        return this;
    }

    public PerformanceShopeeBuilder setFulfillmentPreparationTime(PerformanceShopee.Data fulfillmentPreparationTime) {
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        return this;
    }

    public PerformanceShopeeBuilder setFulfillmentLateShipment(PerformanceShopee.Data fulfillmentLateShipment) {
        this.fulfillmentLateShipment = fulfillmentLateShipment;
        return this;
    }

    public PerformanceShopeeBuilder setCustServiceResponseOverall(PerformanceShopee.Data custServiceResponseOverall) {
        this.custServiceResponseOverall = custServiceResponseOverall;
        return this;
    }

    public PerformanceShopeeBuilder setCustServiceResponseTime(PerformanceShopee.Data custServiceResponseTime) {
        this.custServiceResponseTime = custServiceResponseTime;
        return this;
    }

    public PerformanceShopeeBuilder setCustSatisfactionRatingOverall(PerformanceShopee.Data custSatisfactionRatingOverall) {
        this.custSatisfactionRatingOverall = custSatisfactionRatingOverall;
        return this;
    }

    public PerformanceShopee createPerformanceShopee() {
        return new PerformanceShopee(extractTime, overallPerformance, listingOverall, listingSpam, listingCounterfeit, listingProhibited, listingPreOrderPercent, listingPreOrderExceedTarget, listingOther, nonFulfillmentOverall, nonFulfillmentCancellation, nonFulfillmentReturnRefund, fulfillmentPreparationTime, fulfillmentLateShipment, custServiceResponseOverall, custServiceResponseTime, custSatisfactionRatingOverall);
    }
}
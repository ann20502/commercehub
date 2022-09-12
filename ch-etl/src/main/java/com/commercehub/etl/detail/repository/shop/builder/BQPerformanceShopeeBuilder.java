package com.commercehub.etl.detail.repository.shop.builder;

import com.commercehub.etl.detail.repository.shop.BQPerformanceShopee;

import java.time.Instant;

public class BQPerformanceShopeeBuilder {
    private Instant extractTime;
    private int overallPerformance;
    private BQPerformanceShopee.Data listingOverall;
    private BQPerformanceShopee.Data listingSpam;
    private BQPerformanceShopee.Data listingCounterfeit;
    private BQPerformanceShopee.Data listingProhibited;
    private BQPerformanceShopee.Data listingPreOrderPercent;
    private BQPerformanceShopee.Data listingPreOrderExceedTarget;
    private BQPerformanceShopee.Data listingOther;
    private BQPerformanceShopee.Data nonFulfillmentOverall;
    private BQPerformanceShopee.Data nonFulfillmentCancellation;
    private BQPerformanceShopee.Data nonFulfillmentReturnRefund;
    private BQPerformanceShopee.Data fulfillmentPreparationTime;
    private BQPerformanceShopee.Data fulfillmentLateShipment;
    private BQPerformanceShopee.Data custServiceResponseOverall;
    private BQPerformanceShopee.Data custServiceResponseTime;
    private BQPerformanceShopee.Data custSatisfactionRatingOverall;

    public BQPerformanceShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public BQPerformanceShopeeBuilder setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingOverall(BQPerformanceShopee.Data listingOverall) {
        this.listingOverall = listingOverall;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingSpam(BQPerformanceShopee.Data listingSpam) {
        this.listingSpam = listingSpam;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingCounterfeit(BQPerformanceShopee.Data listingCounterfeit) {
        this.listingCounterfeit = listingCounterfeit;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingProhibited(BQPerformanceShopee.Data listingProhibited) {
        this.listingProhibited = listingProhibited;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingPreOrderPercent(BQPerformanceShopee.Data listingPreOrderPercent) {
        this.listingPreOrderPercent = listingPreOrderPercent;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingPreOrderExceedTarget(BQPerformanceShopee.Data listingPreOrderExceedTarget) {
        this.listingPreOrderExceedTarget = listingPreOrderExceedTarget;
        return this;
    }

    public BQPerformanceShopeeBuilder setListingOther(BQPerformanceShopee.Data listingOther) {
        this.listingOther = listingOther;
        return this;
    }

    public BQPerformanceShopeeBuilder setNonFulfillmentOverall(BQPerformanceShopee.Data nonFulfillmentOverall) {
        this.nonFulfillmentOverall = nonFulfillmentOverall;
        return this;
    }

    public BQPerformanceShopeeBuilder setNonFulfillmentCancellation(BQPerformanceShopee.Data nonFulfillmentCancellation) {
        this.nonFulfillmentCancellation = nonFulfillmentCancellation;
        return this;
    }

    public BQPerformanceShopeeBuilder setNonFulfillmentReturnRefund(BQPerformanceShopee.Data nonFulfillmentReturnRefund) {
        this.nonFulfillmentReturnRefund = nonFulfillmentReturnRefund;
        return this;
    }

    public BQPerformanceShopeeBuilder setFulfillmentPreparationTime(BQPerformanceShopee.Data fulfillmentPreparationTime) {
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        return this;
    }

    public BQPerformanceShopeeBuilder setFulfillmentLateShipment(BQPerformanceShopee.Data fulfillmentLateShipment) {
        this.fulfillmentLateShipment = fulfillmentLateShipment;
        return this;
    }

    public BQPerformanceShopeeBuilder setCustServiceResponseOverall(BQPerformanceShopee.Data custServiceResponseOverall) {
        this.custServiceResponseOverall = custServiceResponseOverall;
        return this;
    }

    public BQPerformanceShopeeBuilder setCustServiceResponseTime(BQPerformanceShopee.Data custServiceResponseTime) {
        this.custServiceResponseTime = custServiceResponseTime;
        return this;
    }

    public BQPerformanceShopeeBuilder setCustSatisfactionRatingOverall(BQPerformanceShopee.Data custSatisfactionRatingOverall) {
        this.custSatisfactionRatingOverall = custSatisfactionRatingOverall;
        return this;
    }

    public BQPerformanceShopee createBQPerformanceShopee() {
        return new BQPerformanceShopee(extractTime, overallPerformance, listingOverall, listingSpam, listingCounterfeit, listingProhibited, listingPreOrderPercent, listingPreOrderExceedTarget, listingOther, nonFulfillmentOverall, nonFulfillmentCancellation, nonFulfillmentReturnRefund, fulfillmentPreparationTime, fulfillmentLateShipment, custServiceResponseOverall, custServiceResponseTime, custSatisfactionRatingOverall);
    }
}
package com.commercehub.etl.domain.entity.shop;

import java.time.Instant;

public class PerformanceBuilder {

    private Instant extractTime;
    private int overallPerformance;
    private Performance.Data listingOverall;
    private Performance.Data listingSpam;
    private Performance.Data listingCounterfeit;
    private Performance.Data listingProhibited;
    private Performance.Data listingPreOrderPercent;
    private Performance.Data listingPreOrderExceedTarget;
    private Performance.Data listingOther;
    private Performance.Data nonFulfillmentOverall;
    private Performance.Data nonFulfillmentCancellation;
    private Performance.Data nonFulfillmentReturnRefund;
    private Performance.Data fulfillmentPreparationTime;
    private Performance.Data fulfillmentLateShipment;
    private Performance.Data custServiceResponseOverall;
    private Performance.Data custServiceResponseTime;
    private Performance.Data custSatisfactionRatingOverall;

    public PerformanceBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public PerformanceBuilder setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
        return this;
    }

    public PerformanceBuilder setListingOverall(Performance.Data listingOverall) {
        this.listingOverall = listingOverall;
        return this;
    }

    public PerformanceBuilder setListingSpam(Performance.Data listingSpam) {
        this.listingSpam = listingSpam;
        return this;
    }

    public PerformanceBuilder setListingCounterfeit(Performance.Data listingCounterfeit) {
        this.listingCounterfeit = listingCounterfeit;
        return this;
    }

    public PerformanceBuilder setListingProhibited(Performance.Data listingProhibited) {
        this.listingProhibited = listingProhibited;
        return this;
    }

    public PerformanceBuilder setListingPreOrderPercent(Performance.Data listingPreOrderPercent) {
        this.listingPreOrderPercent = listingPreOrderPercent;
        return this;
    }

    public PerformanceBuilder setListingPreOrderExceedTarget(Performance.Data listingPreOrderExceedTarget) {
        this.listingPreOrderExceedTarget = listingPreOrderExceedTarget;
        return this;
    }

    public PerformanceBuilder setListingOther(Performance.Data listingOther) {
        this.listingOther = listingOther;
        return this;
    }

    public PerformanceBuilder setNonFulfillmentOverall(Performance.Data nonFulfillmentOverall) {
        this.nonFulfillmentOverall = nonFulfillmentOverall;
        return this;
    }

    public PerformanceBuilder setNonFulfillmentCancellation(Performance.Data nonFulfillmentCancellation) {
        this.nonFulfillmentCancellation = nonFulfillmentCancellation;
        return this;
    }

    public PerformanceBuilder setNonFulfillmentReturnRefund(Performance.Data nonFulfillmentReturnRefund) {
        this.nonFulfillmentReturnRefund = nonFulfillmentReturnRefund;
        return this;
    }

    public PerformanceBuilder setFulfillmentPreparationTime(Performance.Data fulfillmentPreparationTime) {
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        return this;
    }

    public PerformanceBuilder setFulfillmentLateShipment(Performance.Data fulfillmentLateShipment) {
        this.fulfillmentLateShipment = fulfillmentLateShipment;
        return this;
    }

    public PerformanceBuilder setCustServiceResponseOverall(Performance.Data custServiceResponseOverall) {
        this.custServiceResponseOverall = custServiceResponseOverall;
        return this;
    }

    public PerformanceBuilder setCustServiceResponseTime(Performance.Data custServiceResponseTime) {
        this.custServiceResponseTime = custServiceResponseTime;
        return this;
    }

    public PerformanceBuilder setCustSatisfactionRatingOverall(Performance.Data custSatisfactionRatingOverall) {
        this.custSatisfactionRatingOverall = custSatisfactionRatingOverall;
        return this;
    }

    public Performance createPerformance() {
        return new Performance(extractTime, overallPerformance, listingOverall, listingSpam, listingCounterfeit, listingProhibited, listingPreOrderPercent, listingPreOrderExceedTarget, listingOther, nonFulfillmentOverall, nonFulfillmentCancellation, nonFulfillmentReturnRefund, fulfillmentPreparationTime, fulfillmentLateShipment, custServiceResponseOverall, custServiceResponseTime, custSatisfactionRatingOverall);
    }
}
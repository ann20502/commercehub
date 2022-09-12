package com.commercehub.etl.detail.repository.shop.transformer;

import com.commercehub.etl.core.entity.shop.PerformanceShopee;
import com.commercehub.etl.detail.repository.shop.BQPerformanceShopee;
import com.commercehub.etl.detail.repository.shop.builder.BQPerformanceShopeeBuilder;

import java.time.Instant;

public class BQPerformanceShopeeTransformer {

    public static BQPerformanceShopee from(PerformanceShopee performance) {
        return new BQPerformanceShopeeBuilder()
                .setExtractTime(Instant.now())
                .setOverallPerformance(performance.overallPerformance)
                .setListingOverall(transformData(performance.listingOverall))
                .setListingSpam(transformData(performance.listingSpam))
                .setListingCounterfeit(transformData(performance.listingCounterfeit))
                .setListingProhibited(transformData(performance.listingProhibited))
                .setListingPreOrderPercent(transformData(performance.listingPreOrderPercent))
                .setListingPreOrderExceedTarget(transformData(performance.listingPreOrderExceedTarget))
                .setListingOther(transformData(performance.listingOther))
                .setNonFulfillmentOverall(transformData(performance.nonFulfillmentOverall))
                .setNonFulfillmentCancellation(transformData(performance.nonFulfillmentCancellation))
                .setNonFulfillmentReturnRefund(transformData(performance.nonFulfillmentReturnRefund))
                .setFulfillmentPreparationTime(transformData(performance.fulfillmentPreparationTime))
                .setFulfillmentLateShipment(transformData(performance.fulfillmentLateShipment))
                .setCustServiceResponseOverall(transformData(performance.custServiceResponseOverall))
                .setCustServiceResponseTime(transformData(performance.custServiceResponseTime))
                .setCustSatisfactionRatingOverall(transformData(performance.custSatisfactionRatingOverall))
                .createBQPerformanceShopee();
    }

    private static BQPerformanceShopee.Data transformData(PerformanceShopee.Data data) {
        return new BQPerformanceShopee.Data(data.target, data.performance, data.penaltyPoint);
    }

}

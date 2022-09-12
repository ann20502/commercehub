package com.commercehub.etl.detail.repository.shop.transformer;

import com.commercehub.etl.core.entity.shop.PerformanceShopee;
import com.commercehub.etl.core.entitybuilder.shop.PerformanceShopeeBuilder;
import com.commercehub.rest.shopee.output.GetShopPerformanceOutput;

import java.time.Instant;

public class PerformanceShopeeTransformer {

    public static PerformanceShopee from(GetShopPerformanceOutput output) {
        return new PerformanceShopeeBuilder()
                .setExtractTime(Instant.now())
                .setOverallPerformance(output.getResponse().getOverall_performance())
                .setListingOverall(getListingOverall(output))
                .setListingSpam(getListingSpam(output))
                .setListingCounterfeit(getListingCounterfeit(output))
                .setListingProhibited(getListingProhibited(output))
                .setListingPreOrderPercent(getListingPreOrderPercent(output))
                .setListingPreOrderExceedTarget(getListingPreOrderExceedTarget(output))
                .setListingOther(getListingOther(output))
                .setNonFulfillmentOverall(getNonFulfillmentOverall(output))
                .setNonFulfillmentCancellation(getNonFulfillmentCancellation(output))
                .setNonFulfillmentReturnRefund(getNonFulfillmentReturnRefund(output))
                .setFulfillmentPreparationTime(getFulfillmentPreparationTime(output))
                .setFulfillmentLateShipment(getFulfillmentLateShipment(output))
                .setCustServiceResponseOverall(getCustServiceResponseOverall(output))
                .setCustServiceResponseTime(getCustServiceResponseTime(output))
                .setCustSatisfactionRatingOverall(getCustSatisfactionRatingOverall(output))
                .createPerformanceShopee();
    }

    private static PerformanceShopee.Data getListingOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingSpam(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getSpam_listing_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingCounterfeit(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getCounterfeit_ip_infringement_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingProhibited(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getProhibited_listing_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingPreOrderPercent(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getPre_order_listing().getPercent_pre_order_listing_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingPreOrderExceedTarget(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getPre_order_listing().getNumber_of_days_pre_order_listing_exceeds_target_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getListingOther(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getOther_listing_violations().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getNonFulfillmentOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getNonFulfillmentCancellation(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getCancellation_rate_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getNonFulfillmentReturnRefund(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getReturn_refund_rate_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getFulfillmentPreparationTime(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getPreparation_time().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getFulfillmentLateShipment(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getLate_shipment_rate().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getCustServiceResponseOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_service().getResponse_rate().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getCustServiceResponseTime(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_service().getResponse_time().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private static PerformanceShopee.Data getCustSatisfactionRatingOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_satisfaction().getOverall_reviewing_rate().getTotal_data();
        return new PerformanceShopee.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

}

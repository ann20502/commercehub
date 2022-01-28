package com.commercehub.etl.domain.usecase.shop;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.shop.Performance;
import com.commercehub.etl.domain.entity.shop.PerformanceBuilder;
import com.commercehub.etl.domain.repository.ShopRepository;
import com.commercehub.etl.domain.usecase.linking.GetLinking;
import com.commercehub.rest.shopee.ShopeeAccountHealthService;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import com.commercehub.rest.shopee.output.GetShopPerformanceOutput;
import com.google.cloud.Tuple;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.Instant;

@Dependent
public class ExtractShopPerformanceShopee {

    @Inject
    Logger log;

    @Inject
    GetLinking getLinking;

    @Inject
    @RestClient
    ShopeeAccountHealthService shopeeAccountHealthService;

    @Inject
    ShopRepository shopRepository;

    public Multi<Performance> extract() {
        return getLinking()
                .flatMap(linking -> {
                    ShopApiCommonParam param = new ShopApiCommonParam(
                            Integer.parseInt(linking.getPartnerId()),
                            linking.getPartnerSecret(),
                            linking.getAccessToken(),
                            Integer.parseInt(linking.getShopId())
                    );
                    return shopeeAccountHealthService.getShopPerformance(param).toMulti()
                            .map(output -> Tuple.of(linking, output));
                })
                .filter(linkingAndOutput -> {
                    if ( linkingAndOutput.y().getError() != null && !linkingAndOutput.y().getError().isEmpty() ) {
                        log.error("Error while extracting account health: " + linkingAndOutput.y().getError());
                        return false;
                    }
                    return true;
                })
                .map(linkingAndOutput -> {
                    Performance performance = transform(linkingAndOutput.y());
                    log.info(performance);
                    return Tuple.of(linkingAndOutput.x(), performance);
                })
                .flatMap(linkingPerformance -> shopRepository
                        .savePerformance(
                                linkingPerformance.x().getPlatform(),
                                linkingPerformance.x().getShopId(),
                                linkingPerformance.y()
                        )
                        .map(result -> linkingPerformance)
                        .toMulti()
                )
                .map(Tuple::y);
    }

    private Multi<Linking> getLinking() {
        return getLinking.getAll(Linking.STATUS_ACTIVE, true, true)
                .onItem().transformToMulti(items -> Multi.createFrom().iterable(items))
                .filter(linking -> Linking.PLATFORM_SHOPEE.equals(linking.getPlatform()));
    }

    private Performance transform(GetShopPerformanceOutput output) {
        return new PerformanceBuilder()
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
                .createPerformance();
    }

    private Performance.Data getListingOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingSpam(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getSpam_listing_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingCounterfeit(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getCounterfeit_ip_infringement_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingProhibited(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getSevere_listing_violations().getProhibited_listing_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingPreOrderPercent(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getPre_order_listing().getPercent_pre_order_listing_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingPreOrderExceedTarget(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getPre_order_listing().getNumber_of_days_pre_order_listing_exceeds_target_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getListingOther(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getListing_violations().getOther_listing_violations().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getNonFulfillmentOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getNonFulfillmentCancellation(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getCancellation_rate_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getNonFulfillmentReturnRefund(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getNon_fulfillment_rate().getReturn_refund_rate_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getFulfillmentPreparationTime(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getPreparation_time().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getFulfillmentLateShipment(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getFulfillment().getLate_shipment_rate().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getCustServiceResponseOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_service().getResponse_rate().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getCustServiceResponseTime(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_service().getResponse_time().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

    private Performance.Data getCustSatisfactionRatingOverall(GetShopPerformanceOutput output) {
        GetShopPerformanceOutput.Data data = output.getResponse().getCustomer_satisfaction().getOverall_reviewing_rate().getTotal_data();
        return new Performance.Data(data.getTarget(), data.getMy_shop_performance(), data.getPenalty_points());
    }

}

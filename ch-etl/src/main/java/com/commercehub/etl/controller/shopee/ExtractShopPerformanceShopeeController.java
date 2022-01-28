package com.commercehub.etl.controller.shopee;

import com.commercehub.etl.domain.entity.shop.Performance;
import com.commercehub.etl.vm.ShopViewModel;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/performance/extract/shopee")
public class ExtractShopPerformanceShopeeController {

    @Inject
    Logger log;

    @Inject
    ShopViewModel viewModel;

    @POST
    @Operation(summary = "Extract Shop Performance", description = "Extract shop performance from Shopee")
    public Uni<List<Performance>> execute() {
        return viewModel
                .extractShopPerformance()
                .collect().asList()
                .map(performances -> {
                    log.info("Extracted performance for [" + performances.size() + "] shop(s)");
                    return performances;
                });
    }

}

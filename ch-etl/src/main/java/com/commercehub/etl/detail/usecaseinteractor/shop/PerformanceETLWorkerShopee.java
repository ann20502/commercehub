package com.commercehub.etl.detail.usecaseinteractor.shop;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.repository.ShopRepositoryShopee;
import com.commercehub.etl.core.usecase.shop.PerformanceETLWorker;
import com.commercehub.etl.detail.exception.ETLRuntimeException;
import com.commercehub.etl.detail.repository.shop.transformer.PerformanceShopeeTransformer;
import com.commercehub.rest.shopee.ShopeeAccountHealthService;
import com.commercehub.rest.shopee.input.ShopApiCommonParam;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class PerformanceETLWorkerShopee implements PerformanceETLWorker {

    @Inject
    Logger log;

    @Inject
    @RestClient
    ShopeeAccountHealthService shopeeAccountHealthService;

    @Inject
    ShopRepositoryShopee shopRepository;

    @Override
    public Uni<Boolean> extractTransformLoad(Linking input) {
        return Uni.createFrom().item(input)
                .flatMap(linking -> {
                    ShopApiCommonParam param = new ShopApiCommonParam(
                            Integer.parseInt(linking.partnerId),
                            linking.partnerSecret,
                            linking.accessToken,
                            Integer.parseInt(linking.shopId)
                    );
                    return shopeeAccountHealthService.getShopPerformance(param);
                })
                .map(output -> {
                    if ( output.getError() != null && !output.getError().isEmpty() ) {
                        log.error("Error while extracting shopee account health: " + output.getError());
                        throw new ETLRuntimeException("Error while extracting shopee account health: " + output.getError());
                    }
                    return output;
                })
                .map(PerformanceShopeeTransformer::from)
                .flatMap(performance -> shopRepository.savePerformance(input.platform, input.shopId, performance));
    }

}

package com.commercehub.etl.core.usecase.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface OrderExtractorShopee {

    enum ExtractionType {
        NEW,
        UPDATE
    }

    Uni<List<OrderShopee>> extract(Linking linking, TimedTask task, ExtractionType extractionType);

}

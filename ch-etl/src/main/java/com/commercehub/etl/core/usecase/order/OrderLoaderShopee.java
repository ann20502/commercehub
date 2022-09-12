package com.commercehub.etl.core.usecase.order;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.core.entity.scheduler.TimedTask;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface OrderLoaderShopee {

    Uni<Boolean> load(Linking linking, String collectionName, TimedTask task, List<OrderShopee> orders);

}

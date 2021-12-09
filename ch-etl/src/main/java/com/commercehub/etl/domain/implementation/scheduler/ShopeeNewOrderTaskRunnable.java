package com.commercehub.etl.domain.implementation.scheduler;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.schduler.GCPAppEngineCloudTaskRunnable;
import com.commercehub.etl.domain.entity.schduler.TimedTask;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.Map;

@Dependent
public class ShopeeNewOrderTaskRunnable extends GCPAppEngineCloudTaskRunnable {

    @Override
    public String queueName() {
        return "etl-queue";
    }

    @Override
    public String url() {
        return "/order/extract/shopee";
    }

    @Override
    public Map<String,Object> parameters(Linking linking, TimedTask task) {
        Map<String,Object> result = new HashMap<>();
        result.put("documentId", task.getId());
        return result;
    }

}

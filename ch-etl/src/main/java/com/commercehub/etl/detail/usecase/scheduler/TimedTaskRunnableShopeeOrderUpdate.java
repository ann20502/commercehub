package com.commercehub.etl.detail.usecase.scheduler;

import com.commercehub.etl.core.entity.linking.Linking;
import com.commercehub.etl.core.entity.scheduler.TimedTask;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.Map;

@Dependent
public class TimedTaskRunnableShopeeOrderUpdate extends TimedTaskRunnableGCPCloudTask {

    private String baseUri = "";

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    @Override
    public String baseUri() {
        return baseUri;
    }

    @Override
    public String queueName() {
        return "etl-queue";
    }

    @Override
    public String queueLocation() {
        return "us-east1";
    }

    @Override
    public String path() {
        return "/order/update";
    }

    @Override
    public Map<String, Object> parameters(Linking linking, TimedTask task) {
        Map<String,Object> result = new HashMap<>();
        result.put("documentId", task.id);
        result.put("platform", linking.platform);
        return result;
    }

}

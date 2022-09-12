package com.commercehub.etl.detail.repository.boost;

import com.google.cloud.firestore.annotation.IgnoreExtraProperties;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FSBoost {

    private String id;
    private Map<String,Object> data;

    @Inject
    Logger log;

    public FSBoost(String id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Map<String,Integer> getSuccessAttempt() {
        Map<String,Integer> result = new HashMap<>();
        for (Map.Entry<String,Object> entries : data.entrySet() ) {
            if ( isInteger(entries.getValue()) ) {
                result.put(entries.getKey(), ((Long)entries.getValue()).intValue());
            }
        }
        return result;
    }

    private boolean isInteger(Object value) {
        try {
            Integer.parseInt(value.toString());
            return true;
        } catch(NumberFormatException nfe) {
            log.error("Unknown value in boost setting: " + value);
        }
        return false;
    }

}

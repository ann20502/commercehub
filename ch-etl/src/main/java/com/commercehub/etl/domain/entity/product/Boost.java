package com.commercehub.etl.domain.entity.product;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Boost {

    @Exclude
    private String id;

    private Map<String,Object> data;

    @Inject
    Logger log;

    public Boost(String id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public Map<String,Long> getSettings() {
        Map<String,Long> result = new HashMap<>();
        for (Map.Entry<String,Object> entries : data.entrySet() ) {
            if ( isLong(entries.getValue()) ) {
                result.put(entries.getKey(), (Long)entries.getValue());
            }
        }
        return result;
    }

    private boolean isLong(Object value) {
        try {
            Long result = Long.parseLong(value.toString());
            return true;
        } catch(NumberFormatException nfe) {
            System.out.println("Unknown value in boost setting: " + nfe);
        }
        return false;
    }

}

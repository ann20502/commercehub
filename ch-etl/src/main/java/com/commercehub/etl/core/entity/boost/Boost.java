package com.commercehub.etl.core.entity.boost;

import java.util.Map;

public class Boost {

    public final String id;

    public final Map<String,Integer> successAttempt;

    public Boost(String id, Map<String,Integer> successAttempt) {
        this.id = id;
        this.successAttempt = successAttempt;
    }

    @Override
    public String toString() {
        return "Boost{" +
                "id='" + id + '\'' +
                ", successAttempt=" + successAttempt +
                '}';
    }

}

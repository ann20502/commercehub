package com.commercehub.rs.core.entity;

import java.time.Instant;

public class ShopPerformance {

    public final Instant extractTime;
    public final long overallPerformance;
    public final Data rating;
    public final Data fulfillmentPreparationTime;
    public final Data chatResponseTime;

    public ShopPerformance(Instant extractTime, long overallPerformance, Data rating, Data fulfillmentPreparationTime, Data chatResponseTime) {
        this.extractTime = extractTime;
        this.overallPerformance = overallPerformance;
        this.rating = rating;
        this.fulfillmentPreparationTime = fulfillmentPreparationTime;
        this.chatResponseTime = chatResponseTime;
    }

    @Override
    public String toString() {
        return "ShopPerformance{" +
                "extractTime=" + extractTime +
                ", overallPerformance=" + overallPerformance +
                ", rating=" + rating +
                ", fulfillmentPreparationTime=" + fulfillmentPreparationTime +
                ", chatResponseTime=" + chatResponseTime +
                '}';
    }

    public static class Data {
        public final String target;
        public final String performance;
        public final String penaltyPoint;

        public Data(String target, String performance, String penaltyPoint) {
            this.target = target;
            this.performance = performance;
            this.penaltyPoint = penaltyPoint;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "target='" + target + '\'' +
                    ", performance='" + performance + '\'' +
                    ", penaltyPoint='" + penaltyPoint + '\'' +
                    '}';
        }
    }

}

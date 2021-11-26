package com.commercehub.rest.shopee.input;


import com.fasterxml.jackson.annotation.JsonValue;

import javax.ws.rs.QueryParam;

public class GetOrderListInput {

    @QueryParam("time_range_field")
    public final TimeRangeField time_range_field;

    @QueryParam("time_from")
    public final long time_from;

    @QueryParam("time_to")
    public final long time_to;

    @QueryParam("page_size")
    public final int page_size;

    @QueryParam("cursor")
    public final String cursor;

    public GetOrderListInput(TimeRangeField time_range_field, long time_from, long time_to, int page_size, String cursor) {
        this.time_range_field = time_range_field;
        this.time_from = time_from;
        this.time_to = time_to;
        this.page_size = page_size;
        this.cursor = cursor;
    }

    public enum TimeRangeField {
        CREATE_TIME("create_time"),
        UPDATE_TIME("update_time");

        private final String timeRangeField;

        private TimeRangeField(String timeRangeField) {
            this.timeRangeField = timeRangeField;
        }

        @JsonValue
        @Override
        public String toString() {
            return timeRangeField;
        }
    }
}

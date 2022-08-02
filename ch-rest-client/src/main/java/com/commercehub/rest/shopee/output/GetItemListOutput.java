package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetItemListOutput {

    private String request_id;
    private String error;
    private String message;
    private String warning;
    private Response response;

    public String getRequest_id() {
        return request_id;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getWarning() {
        return warning;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "GetItemListOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", warning='" + warning + '\'' +
                ", response=" + response +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        @JsonProperty("item")
        private List<Item> items;

        private int total_count;

        private boolean has_next_page;

        private int next_offset;

        public List<Item> getItems() {
            return items;
        }

        public int getTotal_count() {
            return total_count;
        }

        public boolean isHas_next_page() {
            return has_next_page;
        }

        public int getNext_offset() {
            return next_offset;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "items=" + items +
                    ", total_count=" + total_count +
                    ", has_next_page=" + has_next_page +
                    ", next_offset=" + next_offset +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        private long item_id;
        private String item_status;
        private long update_time;

        public long getItem_id() {
            return item_id;
        }

        public String getItem_status() {
            return item_status;
        }

        public long getUpdate_time() {
            return update_time;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "item_id=" + item_id +
                    ", item_status='" + item_status + '\'' +
                    ", update_time=" + update_time +
                    '}';
        }
    }

}

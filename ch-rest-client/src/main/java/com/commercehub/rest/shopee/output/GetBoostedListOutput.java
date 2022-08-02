package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBoostedListOutput {

    private String error;
    private String message;
    private String warning;
    private String request_id;
    private Response response;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getWarning() {
        return warning;
    }

    public String getRequest_id() {
        return request_id;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "GetBoostedListOutput{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", warning='" + warning + '\'' +
                ", request_id='" + request_id + '\'' +
                ", response=" + response +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        private List<Item> item_list;

        public List<Item> getItem_list() {
            return item_list;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "item_list=" + item_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        private long item_id;
        private long cool_down_second;

        public long getItem_id() {
            return item_id;
        }

        public long getCool_down_second() {
            return cool_down_second;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "item_id=" + item_id +
                    ", cool_down_second=" + cool_down_second +
                    '}';
        }
    }

}

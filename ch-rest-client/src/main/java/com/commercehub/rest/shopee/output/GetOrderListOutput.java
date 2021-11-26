package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrderListOutput {

    private String request_id;
    private String error;
    private String message;
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

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "GetOrderListOutput{" +
                "request_id='" + request_id + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }

    public static class Response {
        private boolean more;
        private String next_cursor;
        private List<Order> order_list;

        public boolean isMore() {
            return more;
        }

        public String getNext_cursor() {
            return next_cursor;
        }

        public List<Order> getOrder_list() {
            return order_list;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "more=" + more +
                    ", next_cursor='" + next_cursor + '\'' +
                    ", order_list=" + order_list +
                    '}';
        }
    }

    public static class Order {
        private String order_sn;
        private String order_status;

        public String getOrder_sn() {
            return order_sn;
        }

        public String getOrder_status() {
            return order_status;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "order_sn='" + order_sn + '\'' +
                    ", order_status='" + order_status + '\'' +
                    '}';
        }
    }

}

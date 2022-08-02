package com.commercehub.rest.shopee.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoostItemOutput {

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
        return "BoostItemOutput{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", warning='" + warning + '\'' +
                ", request_id='" + request_id + '\'' +
                ", response=" + response +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response {

        private List<Failure> failure_list;
        private Success success_list;

        public List<Failure> getFailure_list() {
            return failure_list;
        }

        public Success getSuccess_list() {
            return success_list;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "failure_list=" + failure_list +
                    ", success_list=" + success_list +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Failure {

        private long item_id;
        private String failed_reason;

        public long getItem_id() {
            return item_id;
        }

        public String getFailed_reason() {
            return failed_reason;
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "item_id=" + item_id +
                    ", failed_reason='" + failed_reason + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Success {

        private List<Long> item_id_list;

        public List<Long> getItem_id_list() {
            return item_id_list;
        }

        @Override
        public String toString() {
            return "Success{" +
                    "item_id_list=" + item_id_list +
                    '}';
        }
    }

}

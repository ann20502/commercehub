package com.commercehub.rest.shopee.input;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.ws.rs.QueryParam;
import java.util.List;

public class GetItemListInput {

    @QueryParam("offset")
    public final int offset;

    @QueryParam("page_size")
    public final int page_size;

    @QueryParam("update_time_from")
    public final Long update_time_from;

    @QueryParam("update_time_to")
    public final Long update_time_to;

    @QueryParam("item_status")
    public final List<ItemStatusField> item_status;

    public GetItemListInput(int offset, int page_size, Long update_time_from, Long update_time_to, List<ItemStatusField> item_status) {
        this.offset = offset;
        this.page_size = page_size;
        this.update_time_from = update_time_from;
        this.update_time_to = update_time_to;
        this.item_status = item_status;
    }

    @Override
    public String toString() {
        return "GetItemListInput{" +
                "offset=" + offset +
                ", page_size=" + page_size +
                ", update_time_from=" + update_time_from +
                ", update_time_to=" + update_time_to +
                ", item_status=" + item_status +
                '}';
    }

    public enum ItemStatusField {
        NORMAL("NORMAL"),
        BANNED("BANNED"),
        DELETED("DELETED"),
        UNLIST("UNLIST");

        private final String itemStatusField;

        private ItemStatusField(String itemStatusField) {
            this.itemStatusField = itemStatusField;
        }

        @JsonValue
        @Override
        public String toString() {
            return itemStatusField;
        }
    }

}

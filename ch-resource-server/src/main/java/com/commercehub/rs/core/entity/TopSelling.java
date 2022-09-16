package com.commercehub.rs.core.entity;

import java.util.List;

public class TopSelling {

    public final List<Item> items;

    public TopSelling(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "TopSelling{" +
                "items=" + items +
                '}';
    }

    public static class Item {

        public final long itemId;
        public final String itemName;
        public final String modelName;
        public final String imageUrl;
        public final long sold;

        public Item(long itemId, String itemName, String modelName, String imageUrl, long sold) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.modelName = modelName;
            this.imageUrl = imageUrl;
            this.sold = sold;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    ", modelName='" + modelName + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", sold=" + sold +
                    '}';
        }
    }

}

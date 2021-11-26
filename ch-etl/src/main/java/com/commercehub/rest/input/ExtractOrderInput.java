package com.commercehub.rest.input;

public class ExtractOrderInput {

    public final String collectionName;
    public final String documentId;

    public ExtractOrderInput(String collectionName, String documentId) {
        this.collectionName = collectionName;
        this.documentId = documentId;
    }

}

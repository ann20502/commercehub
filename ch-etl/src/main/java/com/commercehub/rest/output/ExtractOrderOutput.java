package com.commercehub.rest.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractOrderOutput {

    private String collectionName;
    private String documentId;
    private boolean result;

    public ExtractOrderOutput() {}

    public ExtractOrderOutput(String collectionName, String documentId, boolean result) {
        this.collectionName = collectionName;
        this.documentId = documentId;
        this.result = result;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public boolean isResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ExtractOrderOutput{" +
                "collectionName='" + collectionName + '\'' +
                ", documentId='" + documentId + '\'' +
                ", result=" + result +
                '}';
    }

}

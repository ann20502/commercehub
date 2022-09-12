package com.commercehub.etl.detail.repository;

import com.google.cloud.storage.BlobId;

public class BlobIdWrapper {

    public final BlobId blobId;
    public final String uri;

    public BlobIdWrapper(BlobId blobId, String uri) {
        this.blobId = blobId;
        this.uri = uri;
    }

}

package com.commercehub.db.core.usecase;

import io.smallrye.mutiny.Multi;

public interface DatabaseCreation {

    Multi<Boolean> create();

}

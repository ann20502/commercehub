package com.commercehub.db.core.usecase;

import com.commercehub.db.core.entity.Linking;
import io.smallrye.mutiny.Multi;

public interface GetOneLinkPerShop {

    Multi<Linking> execute(boolean hasSetup);

}

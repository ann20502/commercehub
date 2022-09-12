package com.commercehub.etl.core.usecase.product;

import com.commercehub.etl.core.entity.linking.Linking;
import io.smallrye.mutiny.Uni;

public interface ItemETLWorker {

    Uni<Boolean> extractTransformLoad(Linking linking);

}

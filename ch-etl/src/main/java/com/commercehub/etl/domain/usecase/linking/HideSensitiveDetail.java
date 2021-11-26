package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingSimplified;

import javax.enterprise.context.Dependent;

@Dependent
public class HideSensitiveDetail {

    public LinkingSimplified hide(Linking linking) {
        return new LinkingSimplified(linking);
    }

}

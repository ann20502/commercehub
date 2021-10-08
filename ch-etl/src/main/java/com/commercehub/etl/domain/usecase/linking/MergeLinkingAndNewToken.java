package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.etl.domain.entity.Linking;
import com.commercehub.etl.domain.entity.LinkingBuilder;
import com.commercehub.etl.domain.entity.RenewTokenResult;

import javax.enterprise.context.Dependent;
import java.util.Date;

@Dependent
public class MergeLinkingAndNewToken {

    public Linking merge(Linking linking, RenewTokenResult result) {
        long currentTimeMillis = System.currentTimeMillis();
        LinkingBuilder builder = new LinkingBuilder(linking);
        builder.setAccessToken(result.accessToken());
        builder.setRefreshToken(result.refreshToken());
        long accessTokenExpiry = ShopeeUtils.getExpiry(currentTimeMillis, result.accessTokenExpireInSeconds());
        builder.setAccessTokenExpiry(new Date(accessTokenExpiry));
        return builder.createLinking();
    }

}

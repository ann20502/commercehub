package com.commercehub.etl.domain.usecase.linking;

import com.commercehub.common.ShopeeUtils;
import com.commercehub.common.TimeZoneUtils;
import com.commercehub.etl.domain.entity.linking.Linking;
import com.commercehub.etl.domain.entity.linking.LinkingBuilder;
import com.commercehub.etl.domain.service.RenewTokenResult;

import javax.enterprise.context.Dependent;
import java.util.Date;

@Dependent
public class MergeLinkingAndNewToken {

    public Linking merge(Linking linking, RenewTokenResult result) {
        long currentTimeMillis = System.currentTimeMillis();
        long accessTokenExpiry = ShopeeUtils.getExpiry(currentTimeMillis, result.accessTokenExpireInSeconds());
        LinkingBuilder builder = new LinkingBuilder(linking);
        builder.setAccessToken(result.accessToken());
        builder.setRefreshToken(result.refreshToken());
        builder.setAccessTokenExpiry(TimeZoneUtils.getDate(accessTokenExpiry));
        return builder.createLinking();
    }

}

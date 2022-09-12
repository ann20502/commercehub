package com.commercehub.etl.core.repository;

import com.commercehub.etl.core.entity.linking.Linking;

import java.time.Instant;
import java.util.List;

public interface LinkingRepository {

    // randomly return a linking that match the criteria
    List<Linking> getFirst(String platform, String shopId, boolean hasSetup, boolean hasLink);

    List<Linking> getAll(String linkingStatus);

    List<Linking> getAll(String linkingStatus, boolean hasSetup);

    List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink);

    // current time = current time + fast forward minutes
    List<Linking> getAllWithTokenExpired(String linkingStatus, int fastForwardMinute);

    boolean updateToken(String documentId, String accessToken, Instant accessTokenExpiry, String refreshToken);

}

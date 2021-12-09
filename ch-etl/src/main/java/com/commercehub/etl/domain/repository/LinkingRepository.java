package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.linking.Linking;

import java.util.List;

/**
 * Detailed function is meant to reduce DB cost
 */
public interface LinkingRepository {

    // not accurate as there could be more than > 1 entry for platform + shopId
    Linking get(String platform, String shopId, boolean hasSetup, boolean hasLink);

    List<Linking> getAll(String linkingStatus);

    List<Linking> getAll(String linkingStatus, boolean hasSetup);

    List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink);

    /**
     * Current Time = current time + fast-forward minutes
     *
     * @param linkingStatus
     * @param fastForwardMinute
     * @return
     */
    List<Linking> getAllWithTokenExpired(String linkingStatus, int fastForwardMinute);

    boolean updateToken(Linking linking);

}

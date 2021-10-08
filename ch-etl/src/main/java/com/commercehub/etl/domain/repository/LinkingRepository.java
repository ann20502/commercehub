package com.commercehub.etl.domain.repository;

import com.commercehub.etl.domain.entity.Linking;

import java.util.List;

// Detailed function provide with the purpose of reducing DB cost
public interface LinkingRepository {

    List<Linking> getAll(String platformStatus);

    /*
     * Current Time = current time + fast-forward minutes
     */
    List<Linking> getAllWithTokenExpired(String platformStatus, int fastForwardMinute);

    boolean updateToken(Linking linking);

}

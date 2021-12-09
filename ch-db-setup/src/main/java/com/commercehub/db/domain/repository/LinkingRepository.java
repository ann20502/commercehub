package com.commercehub.db.domain.repository;

import com.commercehub.db.domain.entity.Linking;

import java.util.List;

public interface LinkingRepository {

    List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink);

    boolean updateSetup(String documentId, boolean setupResult);

}

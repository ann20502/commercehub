package com.commercehub.db.core.repository;

import com.commercehub.db.core.entity.Linking;

import java.util.List;

public interface LinkingRepository {

    List<Linking> getAll(String linkingStatus, boolean hasSetup, boolean hasLink);

    boolean recordSetup(String documentId, boolean setupResult);

}

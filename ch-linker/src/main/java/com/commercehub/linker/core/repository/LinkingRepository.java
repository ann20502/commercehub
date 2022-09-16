package com.commercehub.linker.core.repository;

import com.commercehub.linker.core.entity.Linking;

import java.util.List;
import java.util.Map;

public interface LinkingRepository {

    List<Linking> getJustOne(String documentId);

    List<Linking> getAll(boolean isLinked);

    List<Linking> getAll(String status);

    boolean update(String documentId, Map<String,Object> fieldToUpdate);

}

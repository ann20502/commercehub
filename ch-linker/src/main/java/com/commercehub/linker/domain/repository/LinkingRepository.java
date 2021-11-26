package com.commercehub.linker.domain.repository;

import com.commercehub.linker.domain.entity.Linking;

import java.util.List;
import java.util.Map;

public interface LinkingRepository {

    List<Linking> getAll(boolean link);

    List<Linking> getAll(String status);

    Linking get(String documentId);

    boolean update(String documentId, Map<String,Object> values);

}

package com.commercehub.linker.domain.repository;

import com.commercehub.linker.domain.entity.Linking;

import java.util.List;

public interface LinkingRepository {

    List<Linking> getAll(String status);

}

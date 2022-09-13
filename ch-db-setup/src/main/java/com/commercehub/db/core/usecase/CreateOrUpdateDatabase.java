package com.commercehub.db.core.usecase;

import com.commercehub.db.core.entity.Linking;

public interface CreateOrUpdateDatabase {

    boolean execute(Linking linking);

}

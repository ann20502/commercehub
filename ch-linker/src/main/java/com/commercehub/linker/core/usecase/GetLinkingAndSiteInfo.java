package com.commercehub.linker.core.usecase;

import com.commercehub.linker.core.entity.LinkingAndSiteInfo;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GetLinkingAndSiteInfo {

    Uni<LinkingAndSiteInfo> getJustOne(String documentId);

    Uni<List<LinkingAndSiteInfo>> getAll(boolean isLinked);

}

package com.commercehub.linker.detail.usecase;

import com.commercehub.common.Utils;
import com.commercehub.linker.core.entity.Linking;
import com.commercehub.linker.core.entity.LinkingAndSiteInfo;
import com.commercehub.linker.core.entity.ModuleConfiguration;
import com.commercehub.linker.core.repository.LinkingRepository;
import com.commercehub.linker.core.usecase.GetLinkingAndSiteInfo;
import com.commercehub.linker.core.usecase.GetModuleConfiguration;
import com.commercehub.linker.detail.common.LinkerUtils;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class GetLinkingAndSiteInfoInteractor implements GetLinkingAndSiteInfo {

    private final LinkingRepository repository;
    private final GetModuleConfiguration getModuleConfiguration;

    public GetLinkingAndSiteInfoInteractor(LinkingRepository repository, GetModuleConfiguration getModuleConfiguration) {
        this.repository = repository;
        this.getModuleConfiguration = getModuleConfiguration;
    }

    @Override
    public Uni<LinkingAndSiteInfo> getJustOne(String documentId) {
        return Uni.createFrom().item(() -> repository.getJustOne(documentId))
                .map(links -> {
                    if ( links.isEmpty() ) { throw new RuntimeException("Invalid document id"); }
                    return links.get(Utils.POS_ZERO);
                })
                .map(this::packWithSiteInfo);
    }

    @Override
    public Uni<List<LinkingAndSiteInfo>> getAll(boolean isLinked) {
        return Uni.createFrom().item(() -> repository.getAll(isLinked))
                .map(links -> {
                    List<LinkingAndSiteInfo> result = new ArrayList<>();
                    for ( Linking link : links ) {
                        result.add(packWithSiteInfo(link));
                    }
                    return result;
                });
    }

    private LinkingAndSiteInfo packWithSiteInfo(Linking linking) {
        ModuleConfiguration configuration = getModuleConfiguration.execute();
        return new LinkingAndSiteInfo(
                linking,
                LinkerUtils.getImagePath(configuration.rootPath, linking.platform)
        );
    }

}

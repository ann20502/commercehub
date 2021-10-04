package com.commercehub.linker.vm;

import com.commercehub.linker.domain.entity.Linking;
import com.commercehub.linker.domain.usecase.GetAvailablePlatformName;
import com.commercehub.linker.domain.usecase.GetExistingLinking;
import com.commercehub.linker.utils.LinkerUtils;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class LinkingViewModel {

    @Inject
    GetExistingLinking getExistingLinking;

    @Inject
    GetAvailablePlatformName getAvailablePlatformName;

    public Uni<List<Linking>> getAll() {
        return Uni.combine().all()
                .unis(
                    getAvailablePlatformName.getAll().collect().asList(),
                    getExistingLinking.getAll(Linking.STATUS_ACTIVE).collect().asList()
                )
                .asTuple()
                .map(tuple -> mergeAvailableAndExisting(tuple.getItem1(), tuple.getItem2()));
    }

    /*
        Don't know how to zip each item with a list ...
        Couldn't find a way in Mutiny documentation ...
     */
    private List<Linking> mergeAvailableAndExisting(List<String> platforms, List<Linking> linkings) {
        return platforms
                .stream()
                .map(platform -> {
                    Linking result = new Linking();

                    for ( Linking linking : linkings ) {
                        if ( platform.equals(linking.getPlatform()) ) {
                            result = linking;
                            break;
                        }
                    }

                    result.setPlatform(platform);
                    result.setLogo(LinkerUtils.getImagePath(platform));
                    return result;
                })
                .collect(Collectors.toList());
    }

}

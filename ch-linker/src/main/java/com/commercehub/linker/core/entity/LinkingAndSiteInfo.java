package com.commercehub.linker.core.entity;

public class LinkingAndSiteInfo {

    public final Linking linking;
    public final String pathLogo;

    public LinkingAndSiteInfo(Linking linking, String pathLogo) {
        this.linking = linking;
        this.pathLogo = pathLogo;
    }

    @Override
    public String toString() {
        return "LinkingAndSiteInfo{" +
                "linking=" + linking +
                ", pathLogo='" + pathLogo + '\'' +
                '}';
    }

}

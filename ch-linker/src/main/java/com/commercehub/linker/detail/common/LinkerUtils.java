package com.commercehub.linker.detail.common;

public class LinkerUtils {

    public static String getImagePath(String rootPath, String platform) {
        return rootPath + "/images/logo-" + platform + ".png";
    }

    public static String getAuthPath(String platform) {
        return "/link/login/" + platform;
    }

}

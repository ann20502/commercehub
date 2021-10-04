package com.commercehub.linker.utils;

public class LinkerUtils {

    public static String getImagePath(String platform) {
        return "/logo-" + platform + ".png";
    }

    public static String getAuthPath(String platform) {
        return "/link/login/" + platform;
    }

}

package com.rentgain.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static boolean isNotNull(Object o) {
        return o != null;
    }

    public static boolean isFileAndExists(String filepath) {
        File file = new File(filepath);
        return file.isFile() && file.exists();
    }


    public static Map<String, String> toMap(String paramString) {
        Map<String, String> toMap = new HashMap<String, String>();
        String[] ampSplit = paramString.split("&");
        for (int i = 0; i < ampSplit.length; i++) {
            String[] equalSplit = ampSplit[i].split("=");
            toMap.put(equalSplit[0], equalSplit[1]);
        }
        return toMap;
    }

    public static String telephoneFormatter(String telephoneNumber) {
        String tel10 = telephoneNumber.substring(telephoneNumber.length() - 10);
        String telFormatted = String.format("+%s %s", telephoneNumber.replace(tel10,""),tel10);
        return telFormatted;
    }
}
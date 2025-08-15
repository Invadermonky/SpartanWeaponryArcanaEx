package com.invadermonky.spartanweaponryarcanaex.util;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;

public class StringHelper {
    public static String getItemTranslationKey(String unloc) {
        return SpartanWeaponryArcanaEx.MOD_ID + ":" + unloc;
    }

    public static String getTranslationKey(String unloc, String type, String... params) {
        StringBuilder str = new StringBuilder(type + "." + SpartanWeaponryArcanaEx.MOD_ID + ":" + unloc);
        for (String param : params) {
            str.append(".").append(param);
        }
        return str.toString();
    }
}

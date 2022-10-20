package com.yuansfer.payment.utils;

import java.util.Map;
import java.util.TreeMap;

public class VerifyUtils {

    public static boolean validateVerifySign(
            Map<String, String> map,
            String yuansferToken,
            String verifySign) {
        boolean flag = false;

        Map<String, String> treeMap = new TreeMap<>(map);

        String str = MapUrlUtils.getUrlParamsByMap(treeMap);

        str = str + "&" + Md5Utils.cryptHash(yuansferToken.trim());

        String calculateValue = Md5Utils.cryptHash(str);

        if (verifySign.equals(calculateValue)) {
            flag = true;
        }
        return flag;
    }
}

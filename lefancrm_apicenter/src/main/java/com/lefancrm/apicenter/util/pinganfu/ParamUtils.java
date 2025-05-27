package com.lefancrm.apicenter.util.pinganfu;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamUtils {

    public static Map<String, String> string2Map(String param) {

        Map<String, String> result = new HashMap<String, String>();

        if (StringUtils.isNotBlank(param)) {

            String[] keyValStr = param.split("&");

            for (String string : keyValStr) {
                String[] keyVal = string.split("=");

                String key = keyVal[0];
                String val = keyVal[1];

                result.put(key, val);

            }

        }

        return result;
    }

}

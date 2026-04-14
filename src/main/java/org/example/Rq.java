package org.example;

import java.util.HashMap;

public class Rq {
    private final String actionName;
    private final HashMap<String, String> paramMap;

    Rq(String cmd) {
        paramMap = new HashMap<String, String>();
        String[] cmdBits = cmd.split("\\?");
        actionName = cmdBits[0];

        String queryString = cmdBits.length > 1 ? cmdBits[1].trim() : "";

        String[] queryStringBits = queryString.split("&");

        for (String queryParam : queryStringBits) {
            String[] queryParamBits = queryParam.split("=");
            String key = queryParamBits[0].trim();
            String value = queryParamBits.length > 1 ? queryParamBits[1].trim() : "";

            if (value.isEmpty()) {
                continue;
            }
            paramMap.put(key, value);
        }

    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String paramName, String defaultValue) {
        return paramMap.getOrDefault(paramName, defaultValue);
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName, "");

        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }


    }

}

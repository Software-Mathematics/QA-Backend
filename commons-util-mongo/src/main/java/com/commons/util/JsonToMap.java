package com.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonToMap {
    public static Map<String, Object> jsonToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();
// convert JSON string to Map
        map = mapper.readValue(json, Map.class);
        System.out.println(map);
        return map;
    }
}

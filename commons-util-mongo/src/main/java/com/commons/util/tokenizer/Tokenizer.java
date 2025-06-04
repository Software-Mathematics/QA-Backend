package com.commons.util.tokenizer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class Tokenizer {
    public static List<String> tokenize(String token, String delim) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(token,delim);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken().trim());
        }
        return list;
    }
}

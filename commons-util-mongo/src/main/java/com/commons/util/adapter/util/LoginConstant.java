package com.commons.util.adapter.util;

import java.util.Map;
import java.util.Set;

public class LoginConstant {

    public static Set<String> restrictedTypes = Set.of("EMAIL", "MOBILE", "USERNAME", "EMAIL||MOBILE");
    public static Map<String, String> keyMap = Map.of(
            "EMAIL", "emailid",
            "MOBILE", "mobileno",
            "USERNAME", "username",
            "EMAIL||MOBILE", "emailid||mobileno"
    );
}

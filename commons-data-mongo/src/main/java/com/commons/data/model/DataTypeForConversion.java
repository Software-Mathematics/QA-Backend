package com.commons.data.model;

import org.springframework.stereotype.Component;

@Component
public class DataTypeForConversion {

    public Object dataTypeForConversion(String type, Object value){
        switch(type) {
            case "class java.lang.Long":
                value =  Long.parseLong((String) value);
                break;
            case "class java.lang.Integer":
                value = Integer.parseInt((String) value);
                break;
            case "class java.lang.int":
                value = Integer.parseInt((String) value);
                break;
            case "class java.lang.long":
                value = Long.parseLong((String) value);
                break;

        }
        return value;


    }

    public static Object conversion(String type, Object value){
        switch(type) {
            case "class java.lang.Long":
                value =  Long.parseLong((String) value);
                break;
            case "class java.lang.Integer":
                value = Integer.parseInt((String) value);
                break;
            case "class java.lang.int":
                value = Integer.parseInt((String) value);
                break;
            case "class java.lang.long":
                value = Long.parseLong((String) value);
                break;

        }
        return value;


    }
}

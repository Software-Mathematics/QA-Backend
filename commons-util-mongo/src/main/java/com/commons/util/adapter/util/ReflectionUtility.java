package com.commons.util.adapter.util;

import com.commons.util.model.dto.BaseDtoI;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//@Component
public class ReflectionUtility {

    public static List<String> getEntityProperties(BaseDtoI obj){
        List<String> fields = new ArrayList<>() ;
        Class<?> kls = obj.getClass();
        Field[] currentFields = kls.getDeclaredFields();
        Field[] supperFields = kls.getSuperclass().getDeclaredFields();
        for (int i =0; i<supperFields.length; i++){
            fields.add(supperFields[i].getName());
        }
        for (int i =0; i<currentFields.length; i++){
            fields.add(currentFields[i].getName());
        }
        return fields;
    }
}

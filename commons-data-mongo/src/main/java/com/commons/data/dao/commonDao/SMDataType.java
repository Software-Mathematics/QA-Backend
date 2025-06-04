package com.commons.data.dao.commonDao;

import com.commons.data.entity.BaseEntity;
import com.commons.data.model.DataTypeForConversion;

import java.lang.reflect.Field;
import java.util.*;

public class SMDataType {

    public static Map<String, Object> findDataTypeV2(Map<String, Object> parameterMap, BaseEntity baseEntity)  {
        Class<?> kls = baseEntity.getClass();
        List<Field> fields = new ArrayList<>();
        Field[] currentFields = kls.getDeclaredFields();
        Field[] supperFields = kls.getSuperclass().getDeclaredFields();
        for (int i = 0; i < supperFields.length; i++) {
            fields.add(supperFields[i]);
        }
        for (int i = 0; i < currentFields.length; i++) {
            fields.add(currentFields[i]);
        }
        for (Field field : fields) {
            if (parameterMap.containsKey(field.getName())) {
                if(parameterMap.get(field.getName()) instanceof List || parameterMap.get(field.getName()) instanceof Date){
                    parameterMap.put(
                            field.getName(),
                            parameterMap.get(field.getName())
                    );
                }else {
                    parameterMap.put(
                            field.getName(),
                            DataTypeForConversion.conversion(
                                    field.getType().toString(),
                                    parameterMap.get(field.getName()).toString()
                            )
                    );
                }
            }
        }

        return parameterMap;
    }
}

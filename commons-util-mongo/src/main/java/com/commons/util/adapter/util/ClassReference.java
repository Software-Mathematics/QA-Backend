package com.commons.util.adapter.util;

import com.commons.data.entity.Attribute;
import com.commons.data.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassReference {

    public BaseEntity getReferenceClass(String classname){
        BaseEntity response = new BaseEntity();
//        switch (classname) {
//            case "Patient":
//                response = new Patient();
//                break;
//            default:
//                response = new Attribute();
//        }
        return response;
    }
}

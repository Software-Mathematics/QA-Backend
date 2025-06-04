package com.commons.util.adapter.util;

import com.commons.data.entity.BaseEntity;

//@Component
public class MyClass extends BaseEntity {
    private String name;

    public MyClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

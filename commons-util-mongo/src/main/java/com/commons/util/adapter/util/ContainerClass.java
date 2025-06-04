package com.commons.util.adapter.util;

//@Component
public class ContainerClass {


    private MyClass myClass;

    public ContainerClass(String className) {
        // Initially, this could be null or a new object.
        // For example, initializing it as a new object:
        this.myClass = new MyClass(className);
    }

    public MyClass getMyAttribute() {
        return myClass;
    }

    public void setMyAttribute(MyClass myAttribute) {
        this.myClass = myAttribute;
    }

    public void updateMyAttribute(String newName) {
        this.myClass = new MyClass(newName);
    }

}

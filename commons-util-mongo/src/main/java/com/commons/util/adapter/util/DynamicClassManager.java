package com.commons.util.adapter.util;

import com.commons.data.entity.BaseEntity;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;

public class DynamicClassManager {
    private static Class<?> dynamicClass = null;

    public static Class<?> getDynamicClass() throws Exception {
        if (dynamicClass == null) {
            synchronized (DynamicClassManager.class) {
                if (dynamicClass == null) {
                    // Define the dynamic class
                    dynamicClass = new ByteBuddy()
                            .subclass(BaseEntity.class)
                            .name("MyDynamicClass")
                            .defineField("name", String.class, java.lang.reflect.Modifier.PRIVATE)
                            .defineConstructor(java.lang.reflect.Modifier.PUBLIC)
                            .withParameter(String.class)
                            .intercept(MethodCall.invoke(BaseEntity.class.getConstructor())
                                    .andThen(FieldAccessor.ofField("name").setsArgumentAt(0)))
                            .defineMethod("getName", String.class, java.lang.reflect.Modifier.PUBLIC)
                            .intercept(FieldAccessor.ofBeanProperty())
                            .defineMethod("setName", void.class, java.lang.reflect.Modifier.PUBLIC)
                            .withParameter(String.class)
                            .intercept(FieldAccessor.ofBeanProperty())
                            .make()
                            .load(DynamicClassManager.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                            .getLoaded();
                }
            }
        }
        return dynamicClass;
    }
}

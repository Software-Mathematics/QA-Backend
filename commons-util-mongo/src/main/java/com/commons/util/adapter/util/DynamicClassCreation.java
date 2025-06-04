package com.commons.util.adapter.util;

import com.commons.data.entity.BaseEntity;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.util.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DynamicClassCreation {

//    @Autowired
//    private static MongoMappingContext mongoMappingContext;

//    @Autowired
//    private static Lazy<MongoMappingContext> lazyMongoMappingContext;




    public static Class<?> getOrCreateDynamicClass(String myClassName, ApplicationContext applicationContext) throws Exception {
        String className = "com.commons.data.entity."
                + (myClassName != null ? myClassName.trim() : "DefaultClass");

        if (DynamicClassCache.isClassInCache(className)) {
            return DynamicClassCache.getClassFromCache(className);
        } else {
            // Create the class dynamically as before
            Class<?> dynamicClass = new ByteBuddy()
                    .subclass(BaseEntity.class)
                    .name(className)
                    // define fields, methods, etc.
//                    .defineField("status", String.class, java.lang.reflect.Modifier.PRIVATE)
                    .defineField("code", String.class, java.lang.reflect.Modifier.PRIVATE)
                    // Generate getter and setter for 'dynamicField'
                    .defineMethod("getCode", String.class, java.lang.reflect.Modifier.PUBLIC)
                    .intercept(FieldAccessor.ofBeanProperty())
                    .defineMethod("setCode", void.class, java.lang.reflect.Modifier.PUBLIC)
                    .withParameter(String.class)
                    .intercept(FieldAccessor.ofBeanProperty())
                    .make()
                    .load(MyClass.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getLoaded();

            // Add the newly created class to the cache
            DynamicClassCache.addClassToCache(className, dynamicClass);
            //Add in dynamicClass into mongo
            MongoMappingContext mongoMappingContext = applicationContext.getBean(MongoMappingContext.class);
            mongoMappingContext.getPersistentEntity(dynamicClass);
            return dynamicClass;
        }
    }
}

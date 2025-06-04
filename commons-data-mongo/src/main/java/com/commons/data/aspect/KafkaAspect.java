package com.commons.data.aspect;

import com.commons.data.entity.BaseEntity;
import com.commons.data.publisher.KafkaPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Map;

@Aspect
@Component
public class KafkaAspect {

    @Autowired
    private KafkaPublisher kafkaPublisher;

//    ObjectMapper objectMapper = new ObjectMapper();
// Configure the ObjectMapper once at initialization
//ObjectMapper objectMapper;

//    public KafkaAspect() {
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);  // Use ISO-8601 date format
//    }
    @Pointcut("execution(* com.commons.data.dao.commonDao.BaseDaoImpl.save(..))")
    public void afterReturningPointCut1(){}

    @AfterReturning(pointcut = "afterReturningPointCut1()",  returning = "retVal")
    public void afterReturning(Object retVal){
        try {
//            objectMapper.registerModule(new JavaTimeModule());
            System.out.println("retVal = " + retVal);
            ObjectMapper objectMapper = JacksonConfig.getObjectMapper();
//            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            Map<String, Object> objectMap = objectMapper.convertValue(retVal, Map.class);
            Object classname = objectMap.get("classname");
            if (classname != null) {
                kafkaPublisher.createProducer(classname + "_UPDATE", objectMap);
                System.out.println("Push to topic");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

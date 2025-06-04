//package com.dailycodebuffer.filemngt.consumer;
//
//import com.commons.util.model.dto.ProductRegistrationDTO;
//import com.dailycodebuffer.filemngt.controller.AttachmentController;
//import com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class KafkaConsumer {
//
//    @Autowired
//    private AttachmentController controller;
////    @KafkaListener(topics = "UPLOAD_INVOICE", groupId = "test")
//    public void listener(String message, HttpServletRequest request) throws Exception {
//        System.out.println("message = " + message);
//        Gson gson = new Gson();
//        ProductRegistrationDTO data = gson.fromJson(message, new TypeToken<ProductRegistrationDTO>() {
//        }.getType());
//        Map<String, Object> dataMap = new HashMap<>();
//        controller.uploadFile(dataMap.toString(), data.getFile(), request);
//
//    }
//}

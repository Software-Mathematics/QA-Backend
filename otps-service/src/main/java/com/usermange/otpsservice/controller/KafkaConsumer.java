package com.usermange.otpsservice.controller;

import com.commons.data.entity.Otps;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class KafkaConsumer {


//    @Autowired
//    private OtpsServiceImpl service;
//
////    @KafkaListener(topics = "${topic.one}", groupId = "template_1")
//    public void consume(String message) throws  IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        System.out.println("message = " + message);
//        try {
//            Gson gson = new Gson();
//            OtpsDTO object = gson.fromJson(message, new TypeToken<OtpsDTO>() {
//            }.getType());
//            service.create(object, object.getTypevalue());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @KafkaListener(topics = "Otps_UPDATE", groupId = "GROUP_02")
    public void otpconsume(String message) throws  IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("message otp = " + message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> objectMap = objectMapper.readValue(message, Map.class);

//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)  // Increase connection timeout
                    .readTimeout(30, TimeUnit.SECONDS)     // Increase read timeout
                    .writeTimeout(30, TimeUnit.SECONDS)    // Increase write timeout
                    .build();

            boolean send = false;
            String mobileNo = (String) objectMap.get("userid");
            String otp = (String) objectMap.get("otp");
            String msg = "";
            if (objectMap.get("otptype") != null && objectMap.get("otptype").toString().equalsIgnoreCase("SMS")) {
                msg = "Dear%20Customer,%20Your%20OTP%20is%20" +
                        otp + ".%20Jaina%20India";
                send = true;
            } else if (objectMap.get("otptype") != null && objectMap.get("otptype").toString().equalsIgnoreCase("HAPPY-CODE")) {
                String reqNo = "";
                String technicianName = "";
                if (objectMap.get("extra_fields") != null){
                    Map<String, Object> extraFields = (Map<String, Object>) objectMap.get("extra_fields");
                    reqNo = extraFields.get("complaint_no") != null ?
                            (String) extraFields.get("complaint_no") : "";
                    technicianName = extraFields.get("technician_name") != null ?
                            (String) extraFields.get("technician_name") : "";
                }

                msg = "Dear Customer, Service Request No.- " +
                        reqNo + " assigned to " +
                        technicianName + ", Pls Provide H-Code : " + otp
                        +" only after satisfactory job completion. Jaina India";
                send = true;
            }else {
                send = false;
            }
            if (send) {
                Response response = null;
                try {
                    Request request = new Request.Builder()
                            .url("http://prpsms.co.in/API/SendMsg.aspx?uname=20240302&pass=LXB6C9G9&send=GICARE&dest="
                                    + mobileNo +
                                    "&msg=" + msg)
                            .get()
                            .build();
                    response = client.newCall(request).execute();
                    System.out.println("response = " + response);
                    System.out.println("message sent !");
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (response != null) {
                        response.close(); // Ensure the response is closed
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
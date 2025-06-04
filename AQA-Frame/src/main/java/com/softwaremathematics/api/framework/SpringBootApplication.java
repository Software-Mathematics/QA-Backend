package com.softwaremathematics.api.framework;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;

import java.util.TimeZone;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    @PostConstruct
    public void init(){
        // Set JVM default timezone to IST, or your desired timezone
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println("✅ Timezone set to Asia/Kolkata");
    }


    public static void main(String[] args) {

        SpringApplication.run(SpringBootApplication.class, args);

    }
}
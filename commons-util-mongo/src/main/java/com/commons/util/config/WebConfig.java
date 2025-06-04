package com.commons.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig  {
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*","http://localhost:4200","http://portal.amwl.in")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .maxAge(-1)   // add maxAge
                        .allowCredentials(false);

            }


        };
    }
}



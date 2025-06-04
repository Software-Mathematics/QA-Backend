package com.dailycodebuffer.filemngt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication

public class SpringBootFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileUploadApplication.class, args);
    }


    @Bean
    public Docket swaggerConfig(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
//				.paths(PathSelectors.ant("/api/ProfileEntity/v2/*"))
                .apis(RequestHandlerSelectors.basePackage("com.dailycodebuffer"))
                .build();
    }

}

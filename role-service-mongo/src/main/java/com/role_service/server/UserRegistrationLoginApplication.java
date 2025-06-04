package com.role_service.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
//@EnableMongoRepositories(basePackages = {"com.commons.data.dao", "com.commons.data.dao.*"})
@EnableMongoRepositories(basePackages = {"com.commons.data.dao.daoImplementation"})
@ComponentScan(basePackages = {"com.commons.*","com.role_service.*","com.commons.util.*"})
@EnableTransactionManagement
@EntityScan({"com.commons.data.entity"})
public class UserRegistrationLoginApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationLoginApplication.class);


    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(UserRegistrationLoginApplication.class, args);
        LOGGER.info("User Management Application Started");
    }

    @Bean
    public Docket swaggerConfig(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
//				.paths(PathSelectors.ant("/api/ProfileEntity/v2/*"))
                .apis(RequestHandlerSelectors.basePackage("com.role_service"))
                .build();
    }


}

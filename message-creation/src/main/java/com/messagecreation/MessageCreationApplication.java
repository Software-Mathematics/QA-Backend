package com.messagecreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackages = {"com.commons.data.dao.daoImplementation", "com.messagecreation.*"})
@ComponentScan(basePackages = {"com.commons.*", "com.messagecreation.*"})
@EnableTransactionManagement
@EntityScan({"com.commons.data.entity", "com.messagecreation.*"})
public class MessageCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageCreationApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig(){
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.messagecreation"))
				.build();
	}
}

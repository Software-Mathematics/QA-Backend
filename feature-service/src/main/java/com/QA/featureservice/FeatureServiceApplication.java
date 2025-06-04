package com.QA.featureservice;

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

@EnableSwagger2
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.commons.data.dao.*")
@ComponentScan(basePackages = { "com.commons.*","com.QA.featureservice.*"})
@EnableTransactionManagement
@EntityScan({"com.commons.data.entity.*","com.QA.featureservice.*"})
public class FeatureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeatureServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfig(){
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.paths(PathSelectors.ant("/api/ProfileEntity/v2/*"))
				.apis(RequestHandlerSelectors.basePackage("com.QA.featureservice"))
				.build();
	}
}

package com.usermange.otpsservice;

import com.commons.data.dao.commonDao.BaseDao;
import com.commons.data.entity.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackages = {"com.commons.data.dao.daoImplementation"})
@ComponentScan(basePackages = {"com.commons.*", "com.usermange.*","com.usermange.otpsservice.controller"})
@EnableTransactionManagement
@EntityScan({"com.commons.data.entity"})
public class OtpsServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(OtpsServiceApplication.class, args);
	}

//	@Bean
//	public ObjectMapper objectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		return objectMapper;
//	}

	@Bean
	public Docket swaggerConfig(){
		return  new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.usermange.otpsservice"))
				.build();
	}


//	@Bean
//	CommandLineRunner commandLineRunner(BaseDao baseDao){
//		return args -> {
//			Map<String, Object> map  = new HashMap<>();
//			map.put("resourcecode", resourceName);
//			map.put("type", "TOPIC");
//			List<Resource> resourceList = baseDao.get(map, new Resource());
//			if (resourceList.size() > 0) {
//				System.out.println(resourceList.get(0));
//			}
//
//		};
//	}

}

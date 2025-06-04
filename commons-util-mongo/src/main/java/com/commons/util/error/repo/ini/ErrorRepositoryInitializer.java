package com.commons.util.error.repo.ini;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.commons.util.model.error.AppError;
import com.commons.util.model.error.ErrorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ErrorRepositoryInitializer implements ApplicationListener<ContextRefreshedEvent> {

	// public static Logger LOG =
	// LoggerFactory.getLogger(ErrorRepositoryInitializer.class.getName());

	@Autowired
	ErrorRepository errorRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent errorRepositoryEvent) {
		// LOG.info("ErrorRepositoryInitializer initialization logic ...");
		ObjectMapper mapper = new ObjectMapper();
		try {
			InputStream errorRepoJsonStream = getClass().getClassLoader().getResourceAsStream("errorRepository.json");
			List<AppError> aceError = Arrays.asList(mapper.readValue(errorRepoJsonStream, AppError[].class));
			errorRepository.setErrors(aceError);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("errorRepository: " + errorRepository.getErrors().toString());
		System.out.println("Error repository initialized....");
		System.out.println("Application started....");
	}
}

package com.cspl.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@Configuration
@EnableSwagger2
@EnableMongoAuditing
public class IntegrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationServiceApplication.class, args);
	}

}

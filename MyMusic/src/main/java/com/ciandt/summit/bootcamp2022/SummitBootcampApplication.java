package com.ciandt.summit.bootcamp2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SummitBootcampApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SummitBootcampApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Starting API MyMusic.");

		SpringApplication.run(SummitBootcampApplication.class, args);

		LOGGER.info("API MyMusic initialized with success");
	}

}

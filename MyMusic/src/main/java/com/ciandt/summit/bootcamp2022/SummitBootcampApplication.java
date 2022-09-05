package com.ciandt.summit.bootcamp2022;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Log4j2
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class SummitBootcampApplication {

	public static void main(String[] args) {

		SpringApplication.run(SummitBootcampApplication.class, args);

		log.info("API MyMusic initialized with success");
	}

}

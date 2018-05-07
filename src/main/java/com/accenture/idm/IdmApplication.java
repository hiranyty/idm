package com.accenture.idm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class IdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdmApplication.class, args);
	}
}

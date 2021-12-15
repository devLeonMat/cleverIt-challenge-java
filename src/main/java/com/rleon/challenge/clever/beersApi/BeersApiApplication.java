package com.rleon.challenge.clever.beersApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BeersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeersApiApplication.class, args);
	}

}

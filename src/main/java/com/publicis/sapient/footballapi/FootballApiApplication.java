package com.publicis.sapient.footballapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class FootballApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FootballApiApplication.class, args);
	}

}

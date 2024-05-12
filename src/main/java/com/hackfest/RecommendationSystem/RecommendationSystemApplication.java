package com.hackfest.RecommendationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class RecommendationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationSystemApplication.class, args);
	}

}

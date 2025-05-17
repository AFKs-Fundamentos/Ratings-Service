package com.pcmaster.afk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsApplication.class, args);
	}

}

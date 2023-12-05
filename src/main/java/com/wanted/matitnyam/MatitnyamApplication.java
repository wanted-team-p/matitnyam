package com.wanted.matitnyam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MatitnyamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatitnyamApplication.class, args);
	}

}

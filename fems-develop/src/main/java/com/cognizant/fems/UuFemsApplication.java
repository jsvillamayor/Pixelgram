package com.cognizant.fems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UuFemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UuFemsApplication.class, args);
	}

}

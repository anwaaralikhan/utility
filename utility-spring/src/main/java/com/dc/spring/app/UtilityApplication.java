package com.dc.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.dc.spring")
@SpringBootApplication
public class UtilityApplication {
	public static void main(String[] args) {
		SpringApplication.run(UtilityApplication.class, args);
	}
}

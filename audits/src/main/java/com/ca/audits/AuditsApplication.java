package com.ca.audits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuditsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AuditsApplication.class, args);
	}
}

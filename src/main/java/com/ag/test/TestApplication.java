package com.ag.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer implements WebApplicationInitializer
	{
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}


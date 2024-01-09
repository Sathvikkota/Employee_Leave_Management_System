package com.klef.jfsd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ElmsprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElmsprojectApplication.class, args);
		System.out.println("EMPLOYEE LEAVE MANAGEMENT SYSTEM");
	}

}

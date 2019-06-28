package com.projectStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
@EntityScan("com.models")
@EnableEurekaClient
public class ProjectStarterClass {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStarterClass.class, args);
	}

}

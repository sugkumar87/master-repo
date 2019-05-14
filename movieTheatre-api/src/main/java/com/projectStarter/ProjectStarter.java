package com.projectStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan("com.*")
@EntityScan("com.model")
//@EnableEurekaClient
public class ProjectStarter {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStarter.class, args);
	
	}

}

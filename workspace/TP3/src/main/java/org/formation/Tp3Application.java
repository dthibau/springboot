package org.formation;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp3Application {

	private static final Logger logger = LoggerFactory
			.getLogger(Tp3Application.class);
	
		
	@Value("${my.email}")
    private String email;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Tp3Application.class, args);

//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			System.out.println(beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
//		}
	}
	
	
	
	
}

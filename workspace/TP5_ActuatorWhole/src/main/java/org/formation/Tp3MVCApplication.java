package org.formation;

import javax.annotation.PostConstruct;

import org.formation.model.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp3MVCApplication {


	
	@Value("${my.email}")
    private String email;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Tp3MVCApplication.class, args);
		
		
//		System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
        
      
	}
	

}

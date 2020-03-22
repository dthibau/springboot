package org.formation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.base.GeneratorBase;

@SpringBootApplication
public class Tp1ConfApplication {


	
	public static void main(String[] args) {
		ApplicationContext ctx =SpringApplication.run(Tp1ConfApplication.class, args);
		
		System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }

        
	}

}

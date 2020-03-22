package org.formation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp1ConfAApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Tp1ConfAApplication.class, args);
		
		String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }
	}
}

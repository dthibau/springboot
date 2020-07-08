package org.formation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Tp1Application.class, args);

//		Arrays.asList(context.getBeanDefinitionNames()).stream()
//				.forEach(s -> System.out.println(s + "=--=" + context.getBean(s).getClass().getSimpleName()));
	}

}

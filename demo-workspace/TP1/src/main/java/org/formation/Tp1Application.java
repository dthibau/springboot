package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Tp1Application.class, args);
		
		
		for ( String beanName : context.getBeanDefinitionNames() ) {
			Object bean = context.getBean(beanName);
			if ( bean.getClass().toString().contains("org.formation") )
			System.out.println("A bean : " + beanName + " of " + bean.getClass() );
		}
	}

}

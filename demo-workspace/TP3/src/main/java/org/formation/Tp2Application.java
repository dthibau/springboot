package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Tp2Application {


	

	public static void main(String[] args) {
		ApplicationContext context = (ApplicationContext)SpringApplication.run(Tp2Application.class, args);
		
		FilterChainProxy filterChain = (FilterChainProxy) context.getBean("springSecurityFilterChain");
		for (SecurityFilterChain chain : filterChain.getFilterChains()) {
			chain.getFilters().stream().forEach(f -> System.out.println(f));
		}
	}

	
}

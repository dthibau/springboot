package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class MVCApplication {

	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MVCApplication.class, args);
		
		FilterChainProxy filterChain = (FilterChainProxy) ctx.getBean("springSecurityFilterChain");
		for (SecurityFilterChain chain : filterChain.getFilterChains()) {
			chain.getFilters().stream().forEach(f -> System.out.println(f));
		}
		
	}
	
	
}

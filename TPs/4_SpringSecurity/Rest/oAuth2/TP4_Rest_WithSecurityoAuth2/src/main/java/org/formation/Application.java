package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Application  {

	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
//		FilterChainProxy filterChain = (FilterChainProxy) ctx.getBean("springSecurityFilterChain");
//		for (SecurityFilterChain chain : filterChain.getFilterChains()) {
//			System.out.println(chain);
//		}
	}
	


}

package org.formation;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class ACLSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().
		authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/**").authenticated()
		.antMatchers("/api/**").hasRole("ADMIN")
			.anyRequest().permitAll()
			.and()
			.formLogin();
	}


	

	
	
}

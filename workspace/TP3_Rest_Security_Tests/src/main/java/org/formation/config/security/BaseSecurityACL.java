package org.formation.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class BaseSecurityACL extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/members").permitAll().antMatchers("/api/members")
				.hasRole("USER").antMatchers("/api/documents").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin();
	}


}

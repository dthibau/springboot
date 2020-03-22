package org.formation;

import java.util.logging.Logger;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = Logger.getLogger(WebSecurityConfig.class.getSimpleName() );
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/", "/home").permitAll()
		    .anyRequest().authenticated()
		    .and()
//		    .httpBasic()
		    .formLogin()
//		    .loginPage("/spring_login").permitAll()
		    .and()
		    .logout().logoutSuccessUrl("/home").logoutUrl("/logout").invalidateHttpSession(true).permitAll();

	}



}
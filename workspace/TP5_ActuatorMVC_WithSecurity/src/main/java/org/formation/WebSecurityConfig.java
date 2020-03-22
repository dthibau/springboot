package org.formation;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
		    .loginPage("/spring_login").permitAll()
		    .and()
		    .logout().logoutSuccessUrl("/home").logoutUrl("/logout").invalidateHttpSession(true).permitAll();

	}



}
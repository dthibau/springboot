package org.formation;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@Profile("memorySecurity")
public class WebSecurityMemoryConfig extends WebSecurityConfig {

	Logger logger = Logger.getLogger(WebSecurityMemoryConfig.class.getSimpleName() );
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}secret").roles("USER");	
	}


}
package org.formation;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile("domainSecurity")
public class WebSecurityDomainConfig extends WebSecurityConfig {

	Logger logger = Logger.getLogger(WebSecurityDomainConfig.class.getSimpleName() );
	
	@Autowired
	UserDetailsService userDetailService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Configuring DetailsService");
		auth.userDetailsService(userDetailService);	
	}


}
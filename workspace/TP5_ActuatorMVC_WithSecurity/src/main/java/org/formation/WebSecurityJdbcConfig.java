package org.formation;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@Profile("jdbcSecurity")
public class WebSecurityJdbcConfig extends WebSecurityConfig {

	Logger logger = Logger.getLogger(WebSecurityJdbcConfig.class.getSimpleName() );
	
	@Autowired
	DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Configuring JDBC");
		auth.jdbcAuthentication().
			dataSource(dataSource).
			usersByUsernameQuery("select email,password,true from member where email = ?").
			authoritiesByUsernameQuery("select email,'USER' from member where email =?");
	}


}
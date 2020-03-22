package org.formation;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile("jdbcSecurity")
@Order(102)
public class SecurityConfigJdbc extends WebSecurityConfigurerAdapter {

	Logger logger = Logger.getLogger(SecurityConfigJdbc.class.getSimpleName() );
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    .antMatchers("/", "/home").permitAll()
		    .anyRequest().authenticated()
		    .and()
//		    .httpBasic()
		    .formLogin()
		    .loginPage("/spring_login").permitAll();
//		    .and()
	//	    .logout().logoutUrl("/logout").permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Configuring JDBC");
//		auth.jdbcAuthentication().
//			dataSource(dataSource).
//			usersByUsernameQuery("select email,password,true from member where email = ?").
//			authoritiesByUsernameQuery("select email,'USER' from member where email =?");
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
        .withUser("tina").password("222").roles("ADMIN")
        .and()
        .withUser("tom").password("444").roles("ADMIN");
	}


}
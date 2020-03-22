package org.formation.config.security;

import org.formation.jwt.JWTConfigurer;
import org.formation.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@Profile("JWT")
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final TokenProvider tokenProvider;

	@Autowired
	public JWTSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests().antMatchers("/api/authenticate").permitAll().antMatchers(HttpMethod.POST, "/api/members").permitAll().antMatchers("/api/members")
		.hasRole("USER").antMatchers("/api/documents").hasRole("ADMIN").anyRequest().authenticated()
	    .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .apply(securityConfigurerAdapter())
        .and()
        .csrf()
        .disable();

	}

	
	private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
	
	@Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	}  

}
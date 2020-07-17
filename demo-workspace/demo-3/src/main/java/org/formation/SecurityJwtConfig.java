package org.formation;

import org.formation.jwt.JWTFilter;
import org.formation.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Profile("jwt")
public class SecurityJwtConfig extends SecurityBaseConfig {

	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	UserDetailsService userDetailsService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Jeton csrf n’est plus nécessaire
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests() // ACLs
			.antMatchers("/swagger-ui.html","/v2/api-docs","/api/authenticate").permitAll()
			.antMatchers(HttpMethod.GET,"/api/**").authenticated()
			.antMatchers("/api/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	
}

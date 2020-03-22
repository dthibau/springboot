package org.formation;

import java.util.logging.Logger;

import org.formation.jwt.JWTConfigurer;
import org.formation.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@Profile("jwtSecurity")
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = Logger.getLogger(JwtSecurityConfig.class.getSimpleName() );
	
	@Autowired
	UserDetailsService userDetailsService;
	
	private final TokenProvider tokenProvider;

	@Autowired
	public JwtSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/api/authenticate").permitAll()
		.antMatchers(HttpMethod.GET, "/api/**").authenticated()
		.antMatchers("/api/**").hasRole("ADMIN")
		.anyRequest().permitAll()
	    .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .apply(securityConfigurerAdapter())
        .and()
        .csrf()
        .disable();

	}

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/**
	 * Not needed in SB 2.x. Il suffit d'avoir un UserDetailsService présent.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Configuring DetailsService");
		auth.userDetailsService(userDetailsService);	
	}
	
	private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

}
package org.formation;

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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Profile("jwt")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/swagger-resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		/** Implémentation sécurité stateful
		 * 
		 */
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/swagger-ui.html","/v2/api-docs").permitAll()
//		    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
//		    .antMatchers("/api/**").hasRole("ADMIN")
//		    .anyRequest().authenticated()
//		    .and()
//		    .formLogin()
//		    .and()
//		    .logout().invalidateHttpSession(true);

		/** Implémentation sécurité stateless JWT
		 * 
		 */
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/swagger-ui.html","/v2/api-docs","/api/authenticate","/actuator/**").permitAll()
	    .antMatchers(HttpMethod.GET,"/api/**").authenticated()
	    .antMatchers("/api/**").hasRole("ADMIN")
	    .anyRequest().authenticated()
	    .and()
	    .apply(new JWTConfigurer(tokenProvider));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	
	
}

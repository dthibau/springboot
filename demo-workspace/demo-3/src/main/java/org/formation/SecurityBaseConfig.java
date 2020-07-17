package org.formation;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityBaseConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/swagger-resources/**","/**/favicon.*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/swagger-ui.html","/v2/api-docs","/actuator/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/**").authenticated()
			.antMatchers("/api/**").hasRole("ADMIN")
			.anyRequest().authenticated()
//			.and()
//			.httpBasic()
			.and().formLogin().permitAll()
			.and()
			.logout().permitAll().invalidateHttpSession(true);
	}
}

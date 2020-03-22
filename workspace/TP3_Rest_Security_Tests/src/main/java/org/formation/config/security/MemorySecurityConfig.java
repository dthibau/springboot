package org.formation.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@Profile("memorySecurity")
public class MemorySecurityConfig extends BaseSecurityACL  {



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}secret").roles("USER").and().withUser("admin")
				.password("{noop}secret").roles("ADMIN");
	}

}

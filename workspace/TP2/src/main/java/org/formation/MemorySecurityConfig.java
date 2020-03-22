package org.formation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("memorySecurity")
public class MemorySecurityConfig extends ACLSecurityConfig {


	@Bean
	public PasswordEncoder passwordEncoder() {
	 return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("secret")).roles("USER")
		.and().withUser("admin").password(passwordEncoder().encode("secret")).roles("ADMIN");
	}
	
	

	
	
}

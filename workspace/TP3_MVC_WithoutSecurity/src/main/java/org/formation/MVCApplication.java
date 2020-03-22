package org.formation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MVCApplication {

	
	@Value("${my.email}")
    private String email;
	
	public static void main(String[] args) {
		SpringApplication.run(MVCApplication.class, args);
		
	}
	
}

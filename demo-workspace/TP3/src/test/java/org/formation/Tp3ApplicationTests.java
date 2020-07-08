package org.formation;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("jwt")
class Tp3ApplicationTests {

	@Autowired 
	ApplicationContext context;
	
	@Test
	void contextLoads() {
		String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).stream().
        filter(s -> !s.startsWith("org.springframework")).
        forEach(s -> System.out.println(s));

	}

}

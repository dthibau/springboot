package org.formation;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Demo3ApplicationTests {

	@Autowired
	ApplicationContext context;
	

	@Test
	void contextLoads() {
		
		Arrays.asList(context.getBeanDefinitionNames()).stream().forEach(System.out::println);
	}


	
}

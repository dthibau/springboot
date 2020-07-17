package org.formation.appli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@WithMockUser(username="user",roles={"USER"})
	public void testGet() throws Exception {
		Map<String,Object> json = this.restTemplate.getForObject("http://localhost:" + port + "/api/members/1",
				Map.class);
		System.out.println(json);
	}
	
	
}

package org.formation.appli;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MockMvcTests {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private MockMvc mvc;


	@Test
	@WithMockUser(username="user",roles={"USER"})
	public void testGet() throws Exception {
		mvc.perform(get("/api/members/1")).andExpect(status().isOk());
	}
	
}

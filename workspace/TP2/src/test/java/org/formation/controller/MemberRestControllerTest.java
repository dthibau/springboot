package org.formation.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.formation.controller.rest.MembersRestController;
import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;




@RunWith(SpringRunner.class)
@WebMvcTest(value=MembersRestController.class)
public class MemberRestControllerTest {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private MemberRepository memberRepository;

	Member aMember;

	@Before
	public void setUp() {
		aMember = new Member();
		aMember.setId(1);
		aMember.setNom("David");
		
		String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
	}

	@Test
	@WithMockUser
	public void testGetMember() throws Exception {
		given(this.memberRepository.findById(1l)).willReturn(Optional.of(aMember));
		ResultActions result = mvc.perform(get("/Members/1"));
		MvcResult mvcResult = result.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		mvc.perform(get("/Members/1")).andExpect(status().isOk()).andExpect(jsonPath("$.nom").value("David"));
	}
}

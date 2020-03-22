package org.formation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Arrays;

import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(SpringRunner.class)
@WebMvcTest(secure=false)
public class SimpleHtmlUnitTest {

	@Autowired
	ApplicationContext context;
	

	// WebClient is auto-configured thanks to HtmlUnit
	@Autowired
	private WebClient webClient;
	
	@Autowired
    private MockMvc mvc;

	@Before
    public void setup() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
	@Test
	public void testExample() throws Exception {

		HtmlPage page = this.webClient.getPage("/");
		assertThat(page.getBody().getTextContent()).contains("Bienvenue");
	}
	
	@Test
	public void mockMvcTest() throws Exception {
		
		mvc.perform(get("/").accept(MediaType.TEXT_HTML)).andExpect(status().isOk());
	}

}

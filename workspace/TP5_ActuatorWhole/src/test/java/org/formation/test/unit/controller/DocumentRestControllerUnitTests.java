package org.formation.test.unit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import org.formation.controller.DocumentsRestController;
import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentRestControllerUnitTests {

	@TestConfiguration
	@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
	public static class UnitConfiguration {
	 
	}
	    
	@MockBean
	private MemberRepository memberRepository;
	
	@MockBean
	private DocumentRepository documentRepository;
	
	@Autowired
	private DocumentsRestController documentsRestController;
	
	@Test
	public void findByOwnerTest() {
		Member myMember = new Member();
		myMember.addDocument(new Document());
		myMember.addDocument(new Document());
		given(this.memberRepository.findById(1)).willReturn(myMember);
		
		System.out.println(documentsRestController.getDocuments("1").size());
		assertThat(documentsRestController.getDocuments("1").size(),is(2));

	}
	
}

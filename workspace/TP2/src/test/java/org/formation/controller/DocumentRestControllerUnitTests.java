package org.formation.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.formation.controller.rest.DocumentsRestController;
import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value=DocumentsRestController.class)
public class DocumentRestControllerUnitTests {

	    
	@MockBean
	private MemberRepository memberRepository;
	
	@MockBean
	private DocumentRepository documentRepository;
	
	@Autowired
	private DocumentsRestController documentsRestController;
	
	@Test
	@WithMockUser
	public void findByOwnerTest() {
		Member myMember = new Member();
		myMember.addDocument(new Document());
		myMember.addDocument(new Document());
		given(this.memberRepository.findById(1l)).willReturn(Optional.of(myMember));
		
		System.out.println(documentsRestController.getDocuments("1").size());
		assertThat(documentsRestController.getDocuments("1").size(),is(2));

	}
	
}

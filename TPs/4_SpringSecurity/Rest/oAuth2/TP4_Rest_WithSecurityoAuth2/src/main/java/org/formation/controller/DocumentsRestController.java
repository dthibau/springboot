package org.formation.controller;

import java.util.List;
import java.util.logging.Logger;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * A RESTFul controller for accessing Document information.
 * 
 * @author David Thibau from Paul Chapman
 */
@RestController
@RequestMapping("/api")
public class DocumentsRestController {

	protected Logger logger = Logger.getLogger(DocumentsRestController.class
			.getName());
	protected MemberRepository memberRepository;
	protected DocumentRepository documentRepository;

	/**
	 * @param documentRepository
	 */
	@Autowired
	public DocumentsRestController(DocumentRepository documentRepository, MemberRepository memberRepository) {
		this.documentRepository = documentRepository;
		this.memberRepository = memberRepository;

	}



	/**
	 * @param owner
	 * @return
	 */
	@RequestMapping("/Documents/owner/{id}/")
	public List<Document> getDocuments(@PathVariable("id") String id) {
		logger.info("Documents-service byOwner() invoked: "
				+ documentRepository.getClass().getName() + " for "
				+ id);

		Member member = memberRepository.findById(Long.parseLong(id));

		return member.getDocuments();

		
	}
	
	@RequestMapping("/Documents")
	public List<Document> getAllDocuments() {
		logger.info("Documents-service getAll() invoked: ");

		List<Document> documents = documentRepository
				.findAll();
		logger.info("Documents-service byOwner() found: " + documents );

		return documents;
		
	}


}

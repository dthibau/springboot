package org.formation.controller.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * A RESTFul controller for accessing Document information.
 * 
 * @author David Thibau from Paul Chapman
 */
@RestController
@RequestMapping("/api/documents")
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
	@GetMapping("/owner/{id}/")
	public List<Document> getDocuments(@PathVariable("id") String id) {
		logger.info("Documents-service byOwner() invoked: "
				+ documentRepository.getClass().getName() + " for "
				+ id);

		Member member = memberRepository.findById(Long.parseLong(id)).orElseThrow( () ->  new MemberNotFoundException(""+id) );


		return member.getDocuments();

		
	}
	
	@PostMapping("/owner/{id}")
	public ResponseEntity<Void> addDocument(@PathVariable("id") long memberId, @Valid @RequestBody Document document) {
		logger.info("Documents-service addDocument() invoked: ");

		
	
		Member member = memberRepository.findById(memberId).orElseThrow( () ->  new MemberNotFoundException(""+memberId) );
		member.addDocument(document);
		memberRepository.save(member);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
		
	}


}

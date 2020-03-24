package org.formation.controller;

import java.util.Set;

import javax.validation.Valid;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {
	protected Logger logger = LoggerFactory
			.getLogger(DocumentRestController.class);

	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	MemberRepository memberRepository;
	
	
	@GetMapping("/owner/{id}/")
	public Set<Document> byOwner(@PathVariable("id") long memberId) throws MemberNotFoundException {
		logger.debug("Documents-service byOwner() invoked:  for {0}", memberId);

		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("id invalide " + memberId));

		return member.getDocuments();
		
	}
	
	@PostMapping("/add/{id}/")
	public ResponseEntity<Void> addDocument(@PathVariable("id") long memberId, @Valid @RequestBody Document document) throws MemberNotFoundException {
		logger.debug("Documents-service addDocument() invoked:  for {0}", memberId);

		Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException("id invalide " + memberId));

		member.addDocument(document);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		
	}
}

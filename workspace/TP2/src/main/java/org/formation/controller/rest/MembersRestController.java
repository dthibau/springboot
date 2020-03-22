package org.formation.controller.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * A RESTFul controller for accessing Member information.
 * 
 * @author David THIBAU
 */
@RestController
@RequestMapping("/api/members")
public class MembersRestController {

	protected Logger logger = Logger.getLogger(MembersRestController.class
			.getName());
	
	
	protected MemberRepository memberRepository;

	/**
	 * Create an instance plugging in the respository of Members.
	 * 
	 * @param MemberRepository
	 *            An Member repository implementation.
	 */
	@Autowired
	public MembersRestController(MemberRepository MemberRepository) {
		this.memberRepository = MemberRepository;

		logger.info("MemberRepository says system has "
				+ MemberRepository.count() + " Members");
	}

	/**
	 * Fetch an Member with the specified Member number.
	 * 
	 * @param MemberNumber
	 *            A numeric, 9 digit Member number.
	 * @return The Member if found.
	 * @throws MemberNotFoundException
	 *             If the number is not recognised.
	 */
	@GetMapping("")
	public List<Member> findAll() {

		logger.info("Members-service findAll() ");
		
		return memberRepository.findAll();
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<Member> byNumber(@PathVariable("memberId") long memberId) {

		logger.info("Members-service byNumber() invoked: " + memberId);
		
		
		Member member = memberRepository.findById(memberId).orElseThrow( () ->  new MemberNotFoundException(""+memberId) );
		logger.info("Members-service byNumber() found: " + member);

		return ResponseEntity.ok().body(member);
	}

	/**
	 * Fetch Members with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../Members/owner/a</code> will find any
	 * Members with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of Members.
	 * @throws MemberNotFoundException
	 *             If there are no matches at all.
	 */
	@GetMapping("/search")
	public List<Member> searchMembers(@RequestParam("q") String partialName) {

		return memberRepository
				.quickSearch(partialName);

		
	}

	@PostMapping(path = "/")
	public ResponseEntity<Member> insert(@Valid @RequestBody Member member) {
		
		member = memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/")
	public ResponseEntity<Member> replace(@Valid @RequestBody Member member) {
		
		memberRepository.findById(member.getId()).orElseThrow( () ->  new MemberNotFoundException(""+member) );

		memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.ACCEPTED);
		
	}
	
	@PatchMapping(path = "/")
	public ResponseEntity<Member> partialUpate(@RequestBody Member member) {
		
		Member originalMember = memberRepository.findById(member.getId()).orElseThrow( () ->  new MemberNotFoundException(""+member) );

		if ( member.getAge() == -1 ) {
			member.setAge(originalMember.getAge());
		}
		if ( member.getEmail() == null ) {
			member.setEmail(originalMember.getEmail());
		}
		if ( member.getNom() == null ) {
			member.setNom(originalMember.getNom());
		}
		if ( member.getPassword() == null ) {
			member.setPassword(originalMember.getPassword());
		}
		if ( member.getPrenom() == null ) {
			member.setPrenom(originalMember.getPrenom());
		}
		
		memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<Member> removeMember(@PathVariable("memberId") long memberId) {
		
		Member member = memberRepository.findById(memberId).orElseThrow( () ->  new MemberNotFoundException(""+memberId) );

		memberRepository.deleteById(member.getId());
		
		return new ResponseEntity<>(member,HttpStatus.ACCEPTED);
		
	}
}

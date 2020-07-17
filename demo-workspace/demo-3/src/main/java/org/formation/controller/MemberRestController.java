package org.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
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

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberRepository memberRepository;
	
	public MemberRestController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@GetMapping(path = "/{id}")
	public Member getById(@PathVariable long id) throws MemberNotFoundException {
		
		return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Bad Id "+id));
	}
	
	@PostMapping
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
		member = memberRepository.save(member);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(member);
	}
	
	@PatchMapping 
	ResponseEntity<Member> merge(Member member) throws MemberNotFoundException {
		Member originalMember = memberRepository.findById(member.getId()).orElseThrow(() -> new MemberNotFoundException("Bad Id"+member.getId()));
		originalMember.setEmail(member.getEmail() != null ? member.getEmail() : originalMember.getEmail() );
		originalMember.setPassword(member.getPassword() != null ? member.getPassword() : originalMember.getPassword());
		originalMember.setPrenom(member.getPrenom() != null ? member.getPrenom() : originalMember.getPrenom() );
		originalMember.setNom(member.getNom() != null ? member.getNom() : originalMember.getNom() );
		originalMember.setAge(member.getAge() != -1 ? member.getAge() : originalMember.getAge() );

		originalMember =  memberRepository.save(originalMember);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
		
	}
	
	@PutMapping
	public ResponseEntity<Member> replace(@Valid Member member) throws MemberNotFoundException {
		memberRepository.findById(member.getId()).orElseThrow(
				() -> new MemberNotFoundException("Id " + member.getId()));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) throws MemberNotFoundException {
		memberRepository.findById(id).orElseThrow(
				() -> new MemberNotFoundException("Id " + id));
		memberRepository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
	
	@GetMapping("/search")
	public List<Member> search(@RequestParam String q) {

		return memberRepository.findByNomContainingIgnoreCase(q);

	}
}

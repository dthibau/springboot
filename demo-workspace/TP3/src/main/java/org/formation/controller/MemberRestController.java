package org.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/{id}")
	public ResponseEntity<Member> getById(@PathVariable long id) throws MemberNotFoundException {

		return ResponseEntity.ok()
				.body(memberRepository.findById(id).orElseThrow(
						() -> new MemberNotFoundException("Id " + id)));

	}
	
	@GetMapping("/search")
	public List<Member> search(@RequestParam String q) throws MemberNotFoundException {

		return memberRepository.findByNomContainingIgnoreCase(q);

	}
	
	@PostMapping()
	public ResponseEntity<Member> create(@Valid Member member) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(memberRepository.save(member));

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

}

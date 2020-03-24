package org.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/{memberId}")
	public Member findById(@PathVariable("memberId") long memberId) throws MemberNotFoundException {
		
		return memberRepository.findById(memberId).orElseThrow( () -> new MemberNotFoundException("Id inconnu"));
	}
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> deleteById(@PathVariable("memberId") long memberId) throws MemberNotFoundException {
		
		Member member = memberRepository.findById(memberId).orElseThrow( () -> new MemberNotFoundException("Id inconnu"));
		
		memberRepository.delete(member);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public Member updateMember(@Valid @RequestBody Member member) {
		if ( memberRepository.existsById(member.getId()) ) {
			member = memberRepository.save(member);
		} else {
			throw new UnsupportedOperationException("Member does not exist");
		}
		return member;
	}

	@PostMapping
	public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
		if ( !(member.getId() > 0) ) {
			member = memberRepository.save(member);
		} else {
			throw new UnsupportedOperationException("Member already exist");
		}
		return new ResponseEntity<>(member,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public List<Member> findAll() {
	
		return memberRepository.findAll();
	}
	
	@GetMapping("/search")
	public List<Member> findMembers(@RequestParam("q") String q) {
	
		return memberRepository.quickSearch(q);
	}
}

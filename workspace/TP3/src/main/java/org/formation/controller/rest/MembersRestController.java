package org.formation.controller.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.formation.controller.User;
import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;



/**
 * A RESTFul controller for accessing Member information.
 * 
 * @author David THIBAU
 */
@RestController
@RequestMapping("/api")
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
	@GetMapping("/members/{memberId}")
	@ApiOperation(value = "Retrieve a member via its Id", response = Member.class)
	public ResponseEntity<Member> byNumber(@PathVariable("memberId") long memberId) {

		logger.info("Members-service byNumber() invoked: " + memberId);
		Member member = memberRepository.findOne(memberId);
		logger.info("Members-service byNumber() found: " + member);

		if (member == null)
			throw new MemberNotFoundException(""+memberId);
		else {
			return ResponseEntity.ok().body(member);
//			return new ResponseEntity<>(member,HttpStatus.OK);
		}
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
	@GetMapping("/SearchMembers")
	public List<Member> searchMembers(@RequestParam("q") String partialName) {

		List<Member> members = memberRepository
				.findByNomContainingIgnoreCase(partialName);

		if (members == null || members.size() == 0)
			throw new MemberNotFoundException(partialName);
		else {
			return members;
		}
		
	}


	@PostMapping(path = "/authenticate")
	public Member authenticate(@Valid @RequestBody User user) {
		logger.info("Members-service authenticate() invoked: " + user);
		Member member = memberRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (member == null )
			throw new MemberNotFoundException(""+user.getEmail());
		else {
			return member;
		}
	}
	
	@PostMapping(path = "/Members")
	@ApiOperation(value = "Register a new member of the service", notes = "Parameter must be a complete member structure. The member containing its id is returned", response = Member.class)
	public ResponseEntity<Member> register(@Valid @RequestBody Member member) {
		
		member = memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.CREATED);
		
	}
}

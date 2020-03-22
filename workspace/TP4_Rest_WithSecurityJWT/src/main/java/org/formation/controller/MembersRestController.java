package org.formation.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
				+ MemberRepository.countMembers() + " Members");
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
	@RequestMapping("/{memberId}")
	public ResponseEntity<Member> byNumber(@PathVariable("memberId") long memberId) {

		logger.info("Members-service byNumber() invoked: " + memberId);
		Member member = memberRepository.findById(memberId);
		logger.info("Members-service byNumber() found: " + member);

		if (member == null)
			throw new MemberNotFoundException(""+memberId);
		else {
			return new ResponseEntity<>(member,HttpStatus.OK);
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
	@RequestMapping("/search")
	public List<Member> searchMembers(@RequestParam("q") String partialName) {

		List<Member> members = memberRepository
				.findByNomContainingIgnoreCase(partialName);


		if (members == null || members.size() == 0)
			throw new MemberNotFoundException(partialName);
		else {
			return members;
		}
		
	}


	@RequestMapping(path = "/find", method = RequestMethod.POST)
	public Member authenticate(@Valid @RequestBody User user) {
		logger.info("Members-service authenticate() invoked: " + user);
		Member member = memberRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (member == null )
			throw new MemberNotFoundException(""+user.getEmail());
		else {
			return member;
		}
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<Member> register(@Valid @RequestBody Member member) {
		
		member = memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.CREATED);
		
	}
}

package org.formation.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



/**
 * A RESTFul controller for accessing Member information.
 * 
 * @author David THIBAU
 */
@RestController
@RequestMapping("/api/members")
@Api(value="members")
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
	@GetMapping("/{memberId}")
	public ResponseEntity<Member> byNumber(@PathVariable("memberId") long memberId) {

		logger.info("Members-service byNumber() invoked: " + memberId);
		Optional<Member> optMember = memberRepository.findById(memberId);
		if ( !optMember.isPresent() ) {
			throw new MemberNotFoundException(""+memberId);
		}
 		logger.info("Members-service byNumber() found: " + optMember.get());

		return ResponseEntity.ok().header("Content-type", "application/json").body(optMember.get());
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

		List<Member> members = memberRepository
				.findByNomContainingIgnoreCase(partialName);


		if (members == null || members.size() == 0)
			throw new MemberNotFoundException(partialName);
		else {
			return members;
		}
		
	}


	@PostMapping(path = "/authenticate")
	public Member authenticate(@Valid @RequestBody UserDTO user) {
		logger.info("Members-service authenticate() invoked: " + user);
		
		Member m = memberRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
			.orElseThrow(() -> new MemberNotFoundException("Invalides login and password"));
		
		return m;
			
		
	}
	
	@PostMapping
	@ApiOperation(value = "Enregistre un nouveau membre dans la base",
    notes = "Les attributs requis sont : email, password, prénom, nom. L'id est généré, la date d'enregistrement est stockée",
    response = Member.class)
	public ResponseEntity<Member> register(@Valid @RequestBody @ApiParam(value = "Un membre avec les champs obligatoires", required = true) Member member) {
		
		member.setRegisteredDate(new Date());
		member = memberRepository.save(member);
		
		return new ResponseEntity<>(member,HttpStatus.CREATED);
		
	}
}

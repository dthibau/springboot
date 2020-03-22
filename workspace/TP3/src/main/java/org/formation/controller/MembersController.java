package org.formation.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;



/**
 * A controller for login, registration and documents.
 * 
 * @author David Thibau
 */
@Controller
@RequestMapping("/web")
@SessionAttributes("loggedUser")
public class MembersController {

	protected Logger logger = Logger.getLogger(MembersController.class
			.getName());
	private final MemberRepository memberRepository;

	/**
	 * Create an instance plugging in the respository of Members.
	 * 
	 * @param MemberRepository
	 *            An Member repository implementation.
	 */
	public MembersController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;

		logger.info("MemberRepository says system has "
				+ memberRepository.count() + " Members");
	}

	@GetMapping(path = "/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping(path = "/authenticate")
	public String authenticate(@Valid User user, BindingResult result, Model model) {
		
		if ( result.hasErrors() ) {
			return "login";
		}
		logger.info("Members-service authenticate() invoked: " + user.getEmail());
		Member member = memberRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (member == null ) {
			result.addError(new ObjectError("errors", "Unable to authenticate"));
			return "login";
		} else {
			member = memberRepository.fullLoad(member.getId());
			model.addAttribute("loggedUser",member);
			return "redirect:documents";
		}
	}
	

}

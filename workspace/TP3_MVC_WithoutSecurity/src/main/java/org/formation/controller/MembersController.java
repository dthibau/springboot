package org.formation.controller;

import java.time.LocalDate;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	protected MemberRepository memberRepository;

	/**
	 * Create an instance plugging in the respository of Members.
	 * 
	 * @param MemberRepository
	 *            An Member repository implementation.
	 */
	@Autowired
	public MembersController(MemberRepository MemberRepository) {
		this.memberRepository = MemberRepository;

		logger.info("MemberRepository says system has "
				+ MemberRepository.countMembers() + " Members");
	}

	@GetMapping(path = "/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("user", new Member());
		return "register";
	}

	@GetMapping(path = "/login")
	public String loginForm(Model model) {
		model.addAttribute("user", new UserDTO());
		return "login";
	}

	@PostMapping(path = "/authenticate")
	public String authenticate(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, Model model, HttpSession session) {
		
		if ( result.hasErrors() ) {
			model.addAttribute("user", user);
			return "login";
		}
		logger.info("Members-service authenticate() invoked: " + user.getEmail());
		Member member = memberRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (member == null ) {
//			throw new MemberNotFoundException(""+user.getEmail());
			model.addAttribute("user", user);
			result.addError(new ObjectError("errors", "Unable to authenticate"));
			return "login";
		} else {
			member = memberRepository.fullLoad(member.getId());
			model.addAttribute("loggedUser",member);
			model.addAttribute("documents",member.getDocuments());
			return "redirect:documents";
		}
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new Member());
		
		return "register";
		
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String register(@Valid Member member, Model model) {
		member.setRegisteredDate(new Date());
		member = memberRepository.save(member);
		
		model.addAttribute("loggedUser",member);

		return "redirect:documents";
		
	}
}

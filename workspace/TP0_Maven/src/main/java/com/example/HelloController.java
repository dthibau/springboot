package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private final HelloProperties props;
	
	public HelloController(HelloProperties props) {
		this.props = props;
	}
	
	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		return props.getGreeting()+" " + name;
	}
}

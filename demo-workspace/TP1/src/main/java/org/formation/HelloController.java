package org.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Value("${my.random}")
	private String myRandom;
	
	@Autowired
	HelloProperties props;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		return props.getGreeting()+" " + name;
	}
	
	@RequestMapping("/random")
	public String random() {
		return  ""+myRandom;
	}
}

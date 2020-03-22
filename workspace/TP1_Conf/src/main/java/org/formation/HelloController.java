package org.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	HelloProperties props;
	
	@Value("${my.random}")
    private int myRandom;
	
	@RequestMapping("/Bonjour")
	public String hello(@RequestParam String name) {
		return props.getGreeting()+ " " + name ;
	}
	
	@RequestMapping("/gut")
	public String coucou(@RequestParam String name) {
		if ( props.getPosition() == 0 )
			return props.getGreeting()+name;
		else 
			return name + props.getGreeting();
	}
	
	@RequestMapping("/random")
	public String random() {
		return  ""+myRandom;
	}
}

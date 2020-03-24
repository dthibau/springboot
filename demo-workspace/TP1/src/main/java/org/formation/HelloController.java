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
	
	@Value("${app.aleatoire}")
	private String aleatoire;

	@RequestMapping("/hello")
	public String hello(@RequestParam String name) {
		return props.getGreeting() + name;
	}
	
	@RequestMapping("/coucou")
	public String coucou(@RequestParam String name) {
		if ( props.getStyleCase().equals(StyleCase.UPPER) ) {
			name = name.toUpperCase();
		}
		if ( props.getStyleCase().equals(StyleCase.LOWER) ) {
			name = name.toLowerCase();
		}
		if ( props.getStyleCase().equals(StyleCase.CAMEL) ) {
			name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase() ;
		}

		if ( props.getPosition() == 0 )
			return props.getGreeting()+ " " + name;
		else 
			return name + " " + props.getGreeting();
	}
	
	@RequestMapping("/random")
	public String random() {
		return  ""+aleatoire;
	}
}

package org.formation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("hello")
public class HelloProperties {

	/**
	 * Greeting message returned by the Hello Rest service.
	 */
	@NotEmpty
	private String greeting;
	@Pattern(regexp = "uppercase|lowercase|camelcase", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String upper = "Upper";
	@Range(min=0,max=1)
	private int position = -1;
	
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getUpper() {
		return upper;
	}
	public void setUpper(String upper) {
		this.upper = upper;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
	
	
}
package org.formation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("hello")
@Validated
public class HelloProperties {
	/** * Greeting message returned by the Hello Rest service. */
	@NotNull
	private String greeting = "Welcome ";
	
	@Pattern(regexp = "Upper|Lower|Camel", flags = Flag.CASE_INSENSITIVE)
	private String anotherStyleCase = "bad";
	
	@NotNull
	private StyleCase styleCase;
	
	@Min(value = 0)
	@Max(value = 1)
	private int position=-1;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public StyleCase getStyleCase() {
		return styleCase;
	}

	public void setStyleCase(StyleCase styleCase) {
		this.styleCase = styleCase;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getAnotherStyleCase() {
		return anotherStyleCase;
	}

	public void setAnotherStyleCase(String anotherStyleCase) {
		this.anotherStyleCase = anotherStyleCase;
	}
	
	
}

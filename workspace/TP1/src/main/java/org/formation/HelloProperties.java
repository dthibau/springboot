package org.formation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("hello")
@Validated
public class HelloProperties {

	@NotEmpty
	private String greeting;
	@Pattern(regexp = "uppercase|lowercase|camelcase", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String style = "Upper";
	@Range(min=0,max=1)
	private int position = -1;
	
	@NotNull
	private CaseEnum caseType;
	
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String upper) {
		this.style = upper;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public CaseEnum getCaseType() {
		return caseType;
	}
	public void setCaseType(CaseEnum caseType) {
		this.caseType = caseType;
	}
	
}
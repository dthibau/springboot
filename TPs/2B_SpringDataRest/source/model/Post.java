package org.formation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590486482810196501L;

	@Id @GeneratedValue
	private long id;
	
	private String title;
	
	@Lob
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	private Member member;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date uploadedDate) {
		this.date = uploadedDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
}

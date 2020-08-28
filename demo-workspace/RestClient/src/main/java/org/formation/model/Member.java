package org.formation.model;

import java.beans.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class Member {


	
	private String email,password;
	
	private String nom,prenom;
	
	private int age;
	

	private Date registeredDate;
	
	private Set<Document> documents = new HashSet<Document>();



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
	public void addDocument(Document document) {
		this.documents.add(document);
	}
	
	@Transient
	public String getNomComplet() {
		return getPrenom() + " " + getNom();
	}


	
}

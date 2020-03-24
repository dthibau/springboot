package org.formation.controller;

public class DocumentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1001254719091979161L;

	public DocumentNotFoundException(String criteria) {
		super("No such document: " + criteria);
	}
}

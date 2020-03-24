package org.formation.controller;

public class MemberNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1001254719091979161L;

	public MemberNotFoundException(String criteria) {
		super("No such member: " + criteria);
	}
}

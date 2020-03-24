package org.formation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundAdvice {

	@ExceptionHandler(value = { MemberNotFoundException.class, DocumentNotFoundException.class })
	ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, Throwable ex) {
		
		return new ResponseEntity<Object>("Entity was not found : " + ex.toString(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}

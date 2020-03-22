package org.formation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotFoundAdvice extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = {MemberNotFoundException.class})
	  ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, Throwable ex) {
	    return new ResponseEntity<Object>(
	          ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	    }
}

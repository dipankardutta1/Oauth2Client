package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  {

	@ExceptionHandler(TokenExpireException.class)
	 public String handleError(TokenExpireException exception) {
		 //return "redirect:/oauth2/authorization/login-client";
		
		return "redirect:/candidate/refresh";
	 }
}

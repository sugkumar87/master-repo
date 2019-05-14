package com.customExceptions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MovieAppException extends Exception {
	
	private String message;
	
	public MovieAppException(String msg) {
		this.message = msg;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
	
}

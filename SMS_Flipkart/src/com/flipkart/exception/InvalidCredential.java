package com.flipkart.exception;

public class InvalidCredential extends Exception{
	
	private String error;
	public InvalidCredential(String error){
		this.error=error;
	}
	public String getError(){
		return error;
	}
}

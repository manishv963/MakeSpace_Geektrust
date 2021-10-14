package com.manishv963.geektrust.exceptions;

public class InvalidInputException extends Exception {

	String message="Invalid Input";
	public InvalidInputException(String message){
		
		this.message=message;
	}
	
	public String toString() {
		
		return message;
	}
}

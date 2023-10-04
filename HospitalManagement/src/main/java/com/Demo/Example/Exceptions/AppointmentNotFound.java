package com.Demo.Example.Exceptions;

public class AppointmentNotFound extends RuntimeException
{

	private String message = "Appiontment Not Found";

	public AppointmentNotFound() {
		
	}
	public String getMessage() {
		return message;
	}
}

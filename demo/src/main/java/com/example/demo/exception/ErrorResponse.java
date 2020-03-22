package com.example.demo.exception;

import java.util.List;
import java.util.Map;

public class ErrorResponse
{
	private String exception;
	
	/*
	 * public ErrorResponse(String message, List<String> details) { super();
	 * this.message = message; this.details = details; }
	 */
 
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    //private List<String> details;
    Map<String, String> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	} 
    
}

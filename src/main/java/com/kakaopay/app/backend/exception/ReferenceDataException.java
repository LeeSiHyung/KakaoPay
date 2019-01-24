package com.kakaopay.app.backend.exception;

public class ReferenceDataException extends RuntimeException {
	  public static final String errorCode = "parent todolist not complete";

	    public ReferenceDataException(String message) {
	        super(message);
	    }

	    public ReferenceDataException(String message, Throwable cause) {
	        super(message, cause);
	    }
}

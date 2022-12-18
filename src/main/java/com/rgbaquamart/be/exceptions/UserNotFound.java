package com.rgbaquamart.be.exceptions;

public class UserNotFound extends RuntimeException{
	public UserNotFound(String exception) {
		super(exception);
	}
}

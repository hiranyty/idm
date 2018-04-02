package com.accenture.idm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnAuthorisedException extends RuntimeException {

	public UnAuthorisedException(String msessage) {
		super(msessage);
	}

}

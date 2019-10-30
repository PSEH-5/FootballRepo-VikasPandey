package com.sapient.football.league.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {
	public CountryNotFoundException(String message) {
		super(message);
	}
}
package com.sapient.football.league.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.football.league.exceptions.CountryNotFoundException;
import com.sapient.football.league.exceptions.LeagueException;
import com.sapient.football.league.exceptions.LeagueNotFoundException;

@ControllerAdvice
@RestController
public class LeagueExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		LeagueException exception = new LeagueException(new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CountryNotFoundException.class)
	public final ResponseEntity<Object> handleCountryNotFoundException(CountryNotFoundException ex, WebRequest request) {
		LeagueException exceptionResponse = new LeagueException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(LeagueNotFoundException.class)
	public final ResponseEntity<Object> handleLeagueNotFoundException(LeagueNotFoundException ex, WebRequest request) {
		LeagueException exceptionResponse = new LeagueException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LeagueException exceptionResponse = new LeagueException(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}

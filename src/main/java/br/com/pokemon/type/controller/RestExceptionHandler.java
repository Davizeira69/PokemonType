package br.com.pokemon.type.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pokemon.type.dto.ResponseDto;
import br.com.pokemon.type.exception.BadRequestException;
import br.com.pokemon.type.exception.ConflictException;
import br.com.pokemon.type.exception.InternalServerError;
import br.com.pokemon.type.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflict(ConflictException conflictException) {
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body((new ResponseDto<>(null, conflictException.getMessage())));
    }
	
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException badRequestException) {
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body((new ResponseDto<>(null, badRequestException.getMessage())));
    }
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException notFoundException) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body((new ResponseDto<>(null, notFoundException.getMessage())));
    }
	
	@ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerError internalServerError) {
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body((new ResponseDto<>(null, internalServerError.getMessage())));
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
		StringBuilder sb = new StringBuilder();
		constraintViolationException.getConstraintViolations()
		.forEach(e -> sb.append(e.getMessage() + " "));
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body((new ResponseDto<>(null, sb.toString())));
    }
}
package br.com.pokemon.type.exception;

public class InternalServerError extends Exception {

private static final long serialVersionUID = 1L;
	
	public InternalServerError(String message) {
		super(message);
	}
}

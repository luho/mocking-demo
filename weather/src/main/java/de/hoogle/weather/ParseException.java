package de.hoogle.weather;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = -5420427039418017178L;

	public ParseException() {
		super();
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}	
}

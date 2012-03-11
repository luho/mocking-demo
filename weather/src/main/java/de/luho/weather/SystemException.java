package de.luho.weather;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -2436915365185302322L;

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}

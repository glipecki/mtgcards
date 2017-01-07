package net.lipecki.mtgcards.execeptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gregorry
 */
public class AppException extends RuntimeException {

	public AppException(final String message) {
		super(message);
	}

	public AppException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public Map<String, Object> asExceptionData() {
		final Map<String, Object> result = new HashMap<>();
		result.put("message", getMessage());
		return result;
	}

}

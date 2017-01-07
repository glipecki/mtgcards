package net.lipecki.mtgcards.execeptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gregorry
 */
public class AppException extends RuntimeException {

	public static AppException of(final String message) {
		return new AppException(message);
	}

	public AppException(final String message) {
		super(message);
		data.put("message", message);
	}

	public AppException(final String message, final Throwable cause) {
		super(message, cause);
		data.put("message", message);
	}

	private final Map<String, Object> data = new HashMap<>();

	public Map<String, Object> asExceptionData() {
		return data;
	}

	public AppException withData(final String key, final Object value) {
		data.put(key, value);
		return this;
	}

}

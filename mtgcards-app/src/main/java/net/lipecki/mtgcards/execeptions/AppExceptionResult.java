package net.lipecki.mtgcards.execeptions;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author gregorry
 */
@Data
@Builder
public class AppExceptionResult {

	private Map<String, Object> appExceptionData;

	private String requestUrl;

}

package net.lipecki.mtgcards.execeptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gregorry
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandlingAdvice {

	@ExceptionHandler(value = AppException.class)
	public AppExceptionResult handleAppExceptions(final HttpServletRequest httpRequest, final AppException appException) {
		log.error("Exception while calling REST endpoint", appException);
		return AppExceptionResult.builder()
				.appExceptionData(appException.asExceptionData())
				.requestUrl(getRequestUrl(httpRequest))
				.build();
	}

	private String getRequestUrl(final HttpServletRequest httpRequest) {
		if (StringUtils.isNotBlank(httpRequest.getQueryString())) {
			return httpRequest.getRequestURI() + "?" + httpRequest.getQueryString();
		} else {
			return httpRequest.getRequestURI();
		}
	}

}

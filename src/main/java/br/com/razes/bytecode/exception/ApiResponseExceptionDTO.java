package br.com.razes.bytecode.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiResponseExceptionDTO {
	
	private String message;
	private HttpStatus httpStatus;
	private ZonedDateTime timesamp;
	
	public ApiResponseExceptionDTO(String message, HttpStatus httpStatus, ZonedDateTime timesamp) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.timesamp = timesamp;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public ZonedDateTime getTimesamp() {
		return timesamp;
	}
	public void setTimesamp(ZonedDateTime timesamp) {
		this.timesamp = timesamp;
	}

}

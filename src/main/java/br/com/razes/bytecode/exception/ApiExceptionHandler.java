package br.com.razes.bytecode.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiResponseExceptionDTO handleAllException(Exception e) {
		
		ApiResponseExceptionDTO exception = new ApiResponseExceptionDTO(
				e.getMessage(), 
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		return exception;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApiRequestException.class)
	public ApiResponseExceptionDTO handleForBusiness(ApiRequestException e) {
		
		ApiResponseExceptionDTO exception = new ApiResponseExceptionDTO(
				e.getMessage(), 
				HttpStatus.BAD_REQUEST, 
				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		return exception;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroBeanValidationMessageDTO> handleForBeanValidation(MethodArgumentNotValidException exception){
		
		List<ErroBeanValidationMessageDTO> messagesErroReturn = new ArrayList<ErroBeanValidationMessageDTO>();
		
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		
		fieldErros.forEach(erroField -> {
			String message = messageSource.getMessage(erroField, LocaleContextHolder.getLocale());
			ErroBeanValidationMessageDTO erroAdd = new ErroBeanValidationMessageDTO(erroField.getField(), message);
			
			messagesErroReturn.add(erroAdd);
		});
		
		return messagesErroReturn;
	}
}

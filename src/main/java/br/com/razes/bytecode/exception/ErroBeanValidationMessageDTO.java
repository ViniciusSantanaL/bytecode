package br.com.razes.bytecode.exception;

public class ErroBeanValidationMessageDTO {
	
	private String field;
	
	private String errorMessage;

	public ErroBeanValidationMessageDTO(String field, String erroMessage) {
		this.field = field;
		this.errorMessage = erroMessage;
	}
	
	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

package br.com.livreteca.error;

public class ValidationError {
	private String field;
	private String message;

	public ValidationError() {
		// TODO Auto-generated constructor stub
	}

	public ValidationError(String message) {
		super();
		this.message = message;
	}

	public ValidationError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package com.commons.util.model.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorRepository{
	private List<AppError> errors;

	public List<AppError> getErrors() {
		return errors;
	}

	public void setErrors(List<AppError> errors) {
		this.errors = errors;
	}

	public void setError(AppError smError) {
		if (this.errors == null) {
			this.errors = new ArrayList<AppError>();
		}

		this.errors.add(smError);
	}

	public AppError getError(String errorCode) {
		int index = this.errors.indexOf(new AppError(errorCode));
		return (index != -1) ? this.errors.get(index) : null;
	}

	@Override
	public String toString() {
		return "ErrorRepository [errors=" + errors + "]";
	}
}
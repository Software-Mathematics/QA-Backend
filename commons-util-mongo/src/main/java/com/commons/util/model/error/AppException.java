package com.commons.util.model.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppException extends Exception {

	private List<AppError> errors;

	public AppException() {
		super();
	}

	public AppException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public AppException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}

	public AppException(List<AppError> errors) {
		super();
		this.errors = errors;
	}

	public AppException(AppError error) {
		super();
		checkAndInitializeErrorsInstance();
		this.errors.add(error);
	}

	public AppException(String exceptionMessage, List<AppError> errors) {
		super(exceptionMessage);
		this.errors = errors;
	}

	public AppException(String exceptionMessage, AppError error) {
		super(exceptionMessage);
		checkAndInitializeErrorsInstance();
		this.errors.add(error);
	}

	public AppException(String exceptionMessage, List<AppError> errors, Throwable t) {
		super(exceptionMessage, t);
		this.errors = errors;
	}

	public AppException(String exceptionMessage, AppError error, Throwable t) {
		super(exceptionMessage, t);
		checkAndInitializeErrorsInstance();
		this.errors.add(error);
	}

	private void checkAndInitializeErrorsInstance() {
		if (!isErrorsInstanceInitialized()) {
			initializeErrorsInstance();
		}
	}

	private boolean isErrorsInstanceInitialized() {
		return (this.errors == null) ? false : true;
	}

	private void initializeErrorsInstance() {
		this.errors = new ArrayList<>();
	}

	/**
	 * @return the errors
	 */
	public List<AppError> getErrors() {
		return errors;
	}

}
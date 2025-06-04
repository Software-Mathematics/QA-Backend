package com.commons.util.exception;

import java.io.Serializable;
import java.util.List;

import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;

public class AppAdaptorException extends AppException implements Serializable {
	public AppAdaptorException() {
		super();
	}

	public AppAdaptorException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public AppAdaptorException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}

	public AppAdaptorException(List<AppError> errors) {
		super(errors);
	}

	public AppAdaptorException(AppError error) {
		super(error);
	}

	public AppAdaptorException(String exceptionMessage, List<AppError> errors) {
		super(exceptionMessage, errors);
	}

	public AppAdaptorException(String exceptionMessage, AppError error) {
		super(exceptionMessage, error);
	}

	public AppAdaptorException(String exceptionMessage, List<AppError> errors, Throwable t) {
		super(exceptionMessage, errors, t);
	}

	public AppAdaptorException(String exceptionMessage, AppError error, Throwable t) {
		super(exceptionMessage, error, t);
	}
}
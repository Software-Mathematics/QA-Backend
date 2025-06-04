package com.commons.util.exception;

import java.io.Serializable;
import java.util.List;

import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;

public class AppServiceException extends AppException implements Serializable {
	public AppServiceException() {
		super();
	}

	public AppServiceException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public AppServiceException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}

	public AppServiceException(List<AppError> errors) {
		super(errors);
	}

	public AppServiceException(AppError error) {
		super(error);
	}

	public AppServiceException(String exceptionMessage, List<AppError> errors) {
		super(exceptionMessage, errors);
	}

	public AppServiceException(String exceptionMessage, AppError error) {
		super(exceptionMessage, error);
	}

	public AppServiceException(String exceptionMessage, List<AppError> errors, Throwable t) {
		super(exceptionMessage, errors, t);
	}

	public AppServiceException(String exceptionMessage, AppError error, Throwable t) {
		super(exceptionMessage, error, t);
	}
}
package com.commons.util.exception;

import java.io.Serializable;
import java.util.List;

import com.commons.util.model.error.AppError;
import com.commons.util.model.error.AppException;

public class AppDBModelAdapterException extends AppException implements Serializable{
	public AppDBModelAdapterException() {
		super();
	}

	public AppDBModelAdapterException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public AppDBModelAdapterException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}

	public AppDBModelAdapterException(List<AppError> errors) {
		super(errors);
	}

	public AppDBModelAdapterException(AppError error) {
		super(error);
	}

	public AppDBModelAdapterException(String exceptionMessage, List<AppError> errors) {
		super(exceptionMessage, errors);
	}

	public AppDBModelAdapterException(String exceptionMessage, AppError error) {
		super(exceptionMessage, error);
	}

	public AppDBModelAdapterException(String exceptionMessage, List<AppError> errors, Throwable t) {
		super(exceptionMessage, errors, t);
	}

	public AppDBModelAdapterException(String exceptionMessage, AppError error, Throwable t) {
		super(exceptionMessage, error, t);
	}
}
package com.commons.util.model;

import java.io.Serializable;
import java.util.List;

import com.commons.util.Operation;
import com.commons.util.Response;
import com.commons.util.Status;
import com.commons.util.model.error.AppError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


public class AppResponseDTO<Type> implements Serializable, Response<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7241603503849466098L;

	private Status requestStatus;
	private Operation operation;
//	@JsonInclude(Include.NON_NULL)
	private Type data;
//	@JsonInclude(Include.NON_NULL)
	List<AppError> errors;

	@Override
	public Type getData() {
		return data;
	}

	@Override
	public Status getRequestStatus() {
		return requestStatus;
	}

	@Override
	public AppResponseDTO<Type> setData(Type data) {
		this.data = data;
		return this;

	}

	@Override
	public AppResponseDTO<Type> setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
		return this;
	}

	public Operation getOperation() {
		return operation;
	}

	public AppResponseDTO<Type> setOperation(Operation operation) {
		this.operation = operation;
		return this;
	}

	@Override
	public List<AppError> getAceErrors() {
		return this.errors;
	}

	@Override
	public void setAceErrors(List<AppError> aceErrors) {
		this.errors = aceErrors;
	}
}
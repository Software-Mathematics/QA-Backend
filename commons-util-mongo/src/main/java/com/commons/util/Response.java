package com.commons.util;

import java.util.List;

import com.commons.util.model.error.AppError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface Response<Type>{
	Status getRequestStatus();

	Response setRequestStatus(Status requestStatus);

	Type getData();

	Response setData(Type data);

	@JsonInclude(Include.NON_NULL)
	List<AppError> getAceErrors();

	void setAceErrors(List<AppError> appErrors);
}
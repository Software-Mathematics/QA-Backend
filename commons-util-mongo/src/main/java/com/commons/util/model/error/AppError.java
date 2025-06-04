package com.commons.util.model.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AppError<Data>{
	private String errorCode;
	private String errorMessage;
	@JsonInclude(Include.NON_NULL)
	private Data data;
	
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 * @param data
	 */
	public AppError(String errorCode, String errorMessage, Data data) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}
	
	public AppError(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public AppError(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppError other = (AppError) obj;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		return true;
	}

	public AppError() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
}
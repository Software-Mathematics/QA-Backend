package com.commons.util.adapter;

import com.commons.util.exception.AppDBModelAdapterException;
import com.commons.util.model.error.AppException;

public interface AppDbModelAdapter<Request, Response>{
	Response adapt(Request request);
//	Response mapList(Request request, Response response)throws Exception;
}
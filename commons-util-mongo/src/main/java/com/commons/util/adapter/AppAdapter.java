package com.commons.util.adapter;

import com.commons.util.exception.AppAdaptorException;

public interface AppAdapter<Request, Response> {
	Response parse(Request request) throws AppAdaptorException;
}
package com.commons.data.multitenancy.exception;

/**
 * User: Gaurav Sharma
 */
public class TenantNotFoundException extends RuntimeException {

    public TenantNotFoundException() {
        super();
    }

    public TenantNotFoundException(String message) {
        super(message);
    }
}

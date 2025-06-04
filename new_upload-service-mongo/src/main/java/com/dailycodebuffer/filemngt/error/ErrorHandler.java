package com.dailycodebuffer.filemngt.error;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    private static final Map<String, String> errorMessages = new HashMap<>();

    static {
        errorMessages.put("K001", "File cannot be empty.");
        errorMessages.put("K002", "Unsupported file type.");
        errorMessages.put("K003", "File not found.");
        errorMessages.put("K004", "Could not store file.");
        errorMessages.put("K005", "File read error.");
        errorMessages.put("K006", "Invalid file metadata.");
        errorMessages.put("K007", "Internal server error.");
    }

    public static String getErrorMessage(String errorCode) {
        return errorMessages.getOrDefault(errorCode, "Unknown error occurred.");
    }
}


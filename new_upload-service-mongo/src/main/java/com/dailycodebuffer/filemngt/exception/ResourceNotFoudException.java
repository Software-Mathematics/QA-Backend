package com.dailycodebuffer.filemngt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoudException extends RuntimeException {
    private boolean status;
    private String message;

}

package com.dailycodebuffer.filemngt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private String id;
    private String filename;
    private String downloadURL;
    private String filetype;
    private Long fileSize;
    private String errorMessage;
    private String errorCode;
    private HttpStatus status;

    public ResponseData(String errorMessage, String errorCode, HttpStatus status) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.status = status;
    }

    public ResponseData(String id, String filename, String downloadURL, String filetype, long fileSize) {
        this.id = id;
        this.filename = filename;
        this.downloadURL = downloadURL;
        this.filetype = filetype;
        this.fileSize = fileSize;
    }
}


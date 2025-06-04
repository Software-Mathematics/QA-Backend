package com.messagecreation.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Payload {
    private String correlId;
    private String msgCode;
    private String msgType;
    private String to;
    private String[] toArr;
    private String[] cc;
    private String[] bcc;
    private String resourceCode;
    private Map<String, Object> modelMap;
    private String tanentid;
}

package com.commons.data.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UploadMapping {

    private String type;
    private String generator;
    private String refcollectionname;
    private String reffieldoperation;

    private Map<String, String> reffields;
    private Map<String, String> returnfields;
    private String delimeter;
    private String srcfield;

    private String srckeytype;
    private String tgtkey;
    private String defaultvalue;



}

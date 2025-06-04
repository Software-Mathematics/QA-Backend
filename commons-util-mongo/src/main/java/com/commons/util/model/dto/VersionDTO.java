package com.commons.util.model.dto;


import lombok.Data;
import java.util.List;

@Data
public class VersionDTO extends BaseDtoI {

    private String code;
    private String name;
    private String versionno;
    private String desc;
    private List devices;
    private String os;
    private  String classname;
    private String priority;
    private String major;
    private String minor;
    private String build;
    private String ischange;
    private String update;
    private String url;
}

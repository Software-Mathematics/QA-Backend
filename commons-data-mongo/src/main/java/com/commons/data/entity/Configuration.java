package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Configuration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuration extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Configuration";
    @Id
    private Long id;
    private String sequence;
    private String readpath;
    private String readfilename;
    private String writepath;
//    private String writefilename;
    private String writefile;
    private String keytocheck;
    private String statusout;
    private String uri;
    private String urimethodtype;
    private String param;
//    private String status;
    private String archiveloc;
    private String delay;
    private String outprop;
    private String statusmap;
//    private String notfound;
    private String listmap;
    private String mapobject;
    private String listmapkeys;
    private String stationcode;
    private String stationname;
    private String arcreq;
//    private String coparc;
//    private String cleancsv;
    private String cleancsvflag;
    private String filetype;
    private String startline;
    private String variablepackage;
    private String wvariablepackage;
//    private String dcflag;
    private String wcfilter;
    private String classname;
    public void setClassname(String classname) {
        this.classname = "Configuration";
    }
}

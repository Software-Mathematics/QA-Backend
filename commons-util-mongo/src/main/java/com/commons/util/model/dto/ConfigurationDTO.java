package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDTO extends BaseDtoI{
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
}

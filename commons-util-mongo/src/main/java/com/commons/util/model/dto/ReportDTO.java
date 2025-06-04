package com.commons.util.model.dto;


import com.commons.data.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO extends BaseDtoI {

    //    private Long id;
    private String code;
    private String linecode;
    private String linename;
    private String modelno;
    private Date date;
    private String type;
    private Event shift;
    private String laststation;
    private String bottleneckcycletime;
    private String operatorefficiencyfactor;
    private String targetno;
    private String actualngpart;
    private String actualokpart;
    private String loss;
    private String actuallineefficiency;
    private String lineefficiencyloss;
    private String availabilityrate;
    private String performancerate;
    private String qualityrate;
    private String oee;
    private String oeeloss;

    private String classname;


}
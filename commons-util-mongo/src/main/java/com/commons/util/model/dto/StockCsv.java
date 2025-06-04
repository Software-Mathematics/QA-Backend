package com.commons.util.model.dto;

import lombok.Data;

@Data
public class StockCsv extends BaseDtoI{
    private String stockreference; //Autogeneration

    private String mmuid;

    private String whid;

    private String blockid;

    private String mmuitemcode;

    private String sapitemcode;
    private String quantityrqst;
    private String itemtype;
    private String quantityapproved;
    private String quantityrcvd;
    private String quantitypndg;

    private String uom;
    private String name;
    private String description;
    private String openingstock;
    private String closingstock;
    private String currentstock;
    private String classname;
    private String mmucode;
    private String blockname;
    private String whname;
    private String mmuname;
    private String itemcode;
    private String quantityindemand;
    private String consumption;
    private String brand;
    private String company;
    private String unit;
    private String salt;

    private String packaging;

}

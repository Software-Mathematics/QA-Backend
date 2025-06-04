package com.commons.util.model.dto;

import com.commons.data.entity.Address;
import com.commons.data.entity.Qualification;

import lombok.Data;



@Data

public class PriceDTO extends BaseDtoI {

    private  String classname;
    private String type;
    private Address address;
    private Qualification qualification;
    private String price;
    private String profileid;
    private String name;
    private String itemcode;
    private String description;
    private String operationtype;
    private String valuetype;
    private String value;
    private String currency;
    private String uom;
    private String unit;
    private String taxinclusiveflag;
    private String code;
    private String pricename;
    private String mmucode;
}

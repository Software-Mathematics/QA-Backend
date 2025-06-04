package com.commons.util.model.dto;

import lombok.Data;

@Data
public class CategoryDTO extends BaseDtoI{
    private String resourcecode;
    private String code;
    private String name;
    private String desc;
    private String type;
    private String hierarchicalcode;
    private String parentdepcode;
    private String parenthierarchicalcode;
    private Integer depth;
    private Boolean isextendedwarranty;
    private String parametername;
    private String parametervalue;
    private String remarks;
    private String shift;
    private Boolean iswarranty;
    private String value;
    private String warrantyinmonth;
    private String classname;
    private String mmucode;
    private String weight;
    private String taxper;
    private String hsn;
    private String price;
    private String cw;
    private String description;
    private String modelcode;
    private String modelno;
    private String productid;
    private String modelid;
    private String category;
    private String extended_warranty_in_month;


}

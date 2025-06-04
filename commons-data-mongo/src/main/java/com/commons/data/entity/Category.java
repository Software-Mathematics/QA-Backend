package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Category")
@Data
public class Category extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Category";
    @Id
    private Long id;
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
    private Boolean iswarranty;
    private String warrantyinmonth;
    private String value;
    private String shift;
    private  String classname;
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

    public void setClassname(String classname) {
        this.classname = "Category";
    }
}

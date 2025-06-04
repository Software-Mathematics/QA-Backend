package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Document(collection = "ProductRegistration")
@Data
public class ProductRegistration extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ProductRegistration";
    @Id
    private Long id;
    private String code;
    private String profileid;
    private Product product;
    private String dateofpurchase;
    private String purchasefromretailer;
    private String title;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String alternatephonenumber;
    private Address address;
    private String description;
    private String category;
    private String invoice;
    Map<String, Object> extradetail;
    private String classname;

     public void setClassname(String classname) {
        this.classname = "ProductRegistration";
    }
}


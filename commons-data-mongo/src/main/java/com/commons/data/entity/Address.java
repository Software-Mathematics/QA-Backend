package com.commons.data.entity;


import com.commons.data.dao.dto.CustomGeoJsonPolygon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Address";
    private  String classname ;
    @Id
    private Long id;
    private String profileid;
    private String addresstype;
    private String defaultaddress;
    private String firstline;
    private String secondline;
    private String postcode;
    private String country;
    private String state;
    private String district;
    private String tehsil;
    private String city;
    private int priority;
    private String locality;
    private String mainaddress;
    private String landmark;
    private String alternateaddress;
    private PinCode pincode;
    private String subdistrict;
    private String mmucode;
    private String contactnumber;

    public void setClassname(String classname) {
        this.classname = "Address";
    }


}
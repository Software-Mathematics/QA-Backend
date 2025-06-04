package com.commons.util.model.dto;


import com.commons.data.dao.dto.CustomGeoJsonPolygon;
import com.commons.data.entity.PinCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO extends BaseDtoI{

//    private Long id;
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
    private String classname;
    private int priority;
    private String locality;
    private String mainaddress;
    private String landmark;
    private String alternateaddress;
    private PinCode pincode;
    private String subdistrict;
    private String mmucode;
    private String contactnumber;
}

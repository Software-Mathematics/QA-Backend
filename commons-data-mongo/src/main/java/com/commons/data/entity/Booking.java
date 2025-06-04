package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Booking";
    private  String classname;
    @Id
    private Long id;
    private String code;
    private String ordernumber;
    private Summary doctor;
    private MMUCreation mmu;
    private Login patient;
    private Patient patientobj;
    private Event slot;
    private String profileid;
    private List<Price> pricelist;
    private String visitid;
    private String paymentmethod;
    private String type;
    private Visit visit;
    private Address patientaddress;
    private List<Category> categorylist;

    public void setClassname(String classname) {
        this.classname = "Booking";
    }
}

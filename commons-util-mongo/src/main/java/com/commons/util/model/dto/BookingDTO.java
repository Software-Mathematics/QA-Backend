package com.commons.util.model.dto;

import com.commons.data.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class BookingDTO extends BaseDtoI{
    private  String classname;

    private String code;
    private String ordernumber;
    private Summary doctor;
    private MMUCreation mmu;
    private Login patient;
    private Patient patientobj;
    private String visitid;
    private Event slot;
    private String profileid;
    private List<Price> pricelist;
    private String paymentmethod;
    private String type;
    private Visit visit;
    private Address patientaddress;
    private List<Category> categorylist;
}

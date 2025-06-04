package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Job")
@Data
public class Job extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Job";
    @Id
    private Long id;
    private String code;
    private String callby;
    private String callsource;
    private String callnature;
    private String purchasesource;
    private String purhcasesubsource;
    private String callreason;
    private String title;
    private String firstname;
    private String lastname;
    private Dealer dealer;
    private Address address;
    private ProductRegistration productregister;
    private String invoiceno;
    private String complaintpriority;
    private String lastcomplaintno;
    private String calltype;
    private String closedreason;
    private String wallmount;
    private String faultreported;
    private String specialinstruction;
    private String ascname;
    private String customeravialabledate;
    private String slot;
    private String estimateTimeResolution;
    private String phonenumber;
    private String email;
    private String alternatephonenumber;
    private String profileid;
    private String issues;
    Map<String, Object> extradetail;
    private int resend_count;
    private String classname;

     public void setClassname(String classname) {
        this.classname = "Job";
    }
}

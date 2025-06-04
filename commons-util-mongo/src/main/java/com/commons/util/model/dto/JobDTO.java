package com.commons.util.model.dto;

import com.commons.data.entity.Address;
import com.commons.data.entity.Dealer;
import com.commons.data.entity.ProductRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO extends BaseDtoI{
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
}

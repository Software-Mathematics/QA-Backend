package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection="InvestmentProfile")
@Data
public class InvestmentProfile extends BaseEntity  {
    @Transient
    public static final String SEQUENCE_NAME = "InvestmentProfile";
    private  String classname;
    @Id
    private Long id;
    private String profileid;
    private String referalcode;
    private String name;
    private String age;
    private String documenttype;
    private String documentnumber;
    private String hierarchicalcode;
    private BigDecimal openingbalance;
    private BigDecimal closingbalance;
    private String currency;
    private BankDetail bankdetail;
    private Integer level;
    private String parentreferalcode;

    public void setClassname(String classname) {
        this.classname = "InvestmentProfile";
    }

}

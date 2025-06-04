package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection="InvestmentItem")
@Data
public class InvestmentItem extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "InvestmentItem";
    private  String classname;
    @Id
    private Long id;
    private String commissiontype;
    private String commission;
    private String investmentterm;
    private Date startdate;
    private Date enddate;
    private Date closingdate;
    private BigDecimal amount;
    private String currency;
    private String profileid;
    private BigDecimal currentvaluation;
    private String txnno;
    private BankDetail bankdetail;



    public void setClassname(String classname) {
        this.classname = "InvestmentItem";
    }

}

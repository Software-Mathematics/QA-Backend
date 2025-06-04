package com.commons.util.model.dto;

import com.commons.data.entity.BankDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentItemDTO extends BaseDtoI {

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
}

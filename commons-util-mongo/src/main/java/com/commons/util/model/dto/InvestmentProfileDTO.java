package com.commons.util.model.dto;

import com.commons.data.entity.BankDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentProfileDTO extends BaseDtoI{

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
}

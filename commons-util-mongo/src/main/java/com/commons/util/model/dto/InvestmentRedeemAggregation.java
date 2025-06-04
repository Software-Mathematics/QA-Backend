package com.commons.util.model.dto;

import com.commons.data.entity.InvestmentProfile;
import lombok.Data;

import java.util.List;

@Data
public class InvestmentRedeemAggregation {
    private InvestmentProfile parent;
    private List<InvestmentProfile> child;
}

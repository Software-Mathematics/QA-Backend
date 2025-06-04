package com.commons.util.model.dto;

import com.commons.data.entity.InvestmentItem;
import com.commons.data.entity.InvestmentProfile;
import lombok.Data;

import java.util.List;

@Data
public class InvestmentProfileAggregationV2 {
    private InvestmentProfile investmentprofile;
    private InvestmentItem investmentitem;
}

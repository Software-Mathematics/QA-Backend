package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerDTO extends BaseDtoI{
    private String dealer;
    private String name;
    private String mobileno;
    private String pincode;
    private String profileid;
}

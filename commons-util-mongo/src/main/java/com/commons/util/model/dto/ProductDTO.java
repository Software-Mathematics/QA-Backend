package com.commons.util.model.dto;

import com.commons.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDtoI{
    private String serialnumber;
    private Category brand;
    private Category product;
    private Category model;
    private String warrantyinmonth;
    private Boolean iswarranty;
    private Boolean isextendedwarranty;
    private String extendedwarrantyinmonth;
    private String profileid;

}

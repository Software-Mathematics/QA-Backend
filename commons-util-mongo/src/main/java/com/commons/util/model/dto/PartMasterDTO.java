package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartMasterDTO extends BaseDtoI{
    private String modelid;
    private String partcode;
    private String partdescription;
    private String sfprice;
    private String customerprice;
    private String bomqty;
    private String hsn;
    private String weight;
    private String bomtype;
    private String exwarrantydays;
    private String valueapproval;
    private String safetybox;
    private String paidwarranty;
    private String isserialized;
    private String classname;
}

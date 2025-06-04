package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItembatchDTO extends BaseDtoI {

    private String itemcode;
    private String itemid;
    private String quantity;
    private String consumed;
    private String batchno;
    private Date manufacturingdate;
    private Date expairydate;
    private String type;
    private String uniquecode;
    private String displayname;
    private String classname;
}

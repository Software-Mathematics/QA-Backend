package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LossMasterDTO extends BaseDtoI {

    //    private Long id;
    private String lose_type;
    private Object value;
    private String updateddate;

    private  String classname;

}
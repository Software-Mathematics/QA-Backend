package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemarkDTO extends BaseDtoI {

    //    private Long id;
    private String task_code;
    private String remark;
    private  String classname;

}
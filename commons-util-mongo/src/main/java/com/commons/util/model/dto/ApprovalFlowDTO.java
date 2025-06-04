package com.commons.util.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalFlowDTO extends BaseDtoI{

    private Long id;
    private String entityname;
    private String stage;
    private String stagetype;
    private String classname;

}

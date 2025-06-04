package com.commons.util.model.responses;

import com.commons.util.model.dto.DepartmentDTO;
import com.commons.util.model.dto.DesignationDTO;
import com.commons.util.model.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class MessagesResponse {
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GetAllDTO getAllDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DepartmentDTO departmentDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DepartmentDTO> departmentDTOS;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DesignationDTO designationDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DesignationDTO> designationDTOS;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RoleDTO roleDTO;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoleDTO> roleDTOS;
}

package com.commons.util.model.responses;


import com.commons.util.model.dto.RoleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RoleDTO roleDTO;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoleDTO> roleDTOList;
}

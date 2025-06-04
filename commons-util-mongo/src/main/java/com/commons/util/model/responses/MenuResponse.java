package com.commons.util.model.responses;

import com.commons.data.entity.MasterMenu;
import com.commons.util.model.dto.MasterMenuDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {
    @JsonInclude(Include.NON_NULL)
    private List<MasterMenuDTO> masterMenus;
}

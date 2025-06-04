package com.commons.util.model.dto;

import com.commons.data.entity.Machine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationAssociationDTO extends BaseDtoI{
    private String stationcode;
    private Machine machine;
}

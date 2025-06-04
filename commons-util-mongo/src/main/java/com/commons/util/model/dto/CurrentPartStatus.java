package com.commons.util.model.dto;

import com.commons.data.entity.PartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentPartStatus extends BaseDtoI{
    private PartStatus header;
    private List<PartStatus> stations;
}

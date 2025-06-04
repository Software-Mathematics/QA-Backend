package com.commons.util.model.dto;

import com.commons.data.entity.Item;
import com.commons.data.entity.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BOMCreationDTO extends BaseDtoI{
    private List<Item> items;
    private String modelno;
    private String stationname;
    private String name;
    private String description;
    private String pattern;
    private String processid;
}

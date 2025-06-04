package com.commons.util.model.dto;

import com.commons.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListDTO extends BaseDtoI{
    private String stationcode;
    private Category category;
    private String code;

}

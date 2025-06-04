package com.commons.util.model.dto;

import com.commons.data.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class TreeDTO {
    private long id;
    private BaseDtoI dto;
    private String name;
    private List<TreeDTO> children;
}

package com.commons.util.model.dto;

import com.commons.data.entity.PageStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageStructureDTO extends BaseDtoI {

    //    private Long id;
    private String tagName;
    private String attributeName;
    private String attributeValue;
    private List<PageStructure> children;

    private String classname;

}
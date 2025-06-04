package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentMasterDTO extends BaseDtoI {

    //    private Long id;
    private String type;
    private String module_id;
    private String title;
    private String description;
    private String is_active;
    private String is_mandatory;
    private List<Map<String, String>> content;
    private String resourcecode;

    private String classname;

}
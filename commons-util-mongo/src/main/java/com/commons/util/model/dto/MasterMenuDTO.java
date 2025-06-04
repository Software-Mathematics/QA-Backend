package com.commons.util.model.dto;

import com.commons.data.entity.ChildrenMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MasterMenuDTO {
    @JsonProperty("menuid")
    private Long menuid;
    @JsonProperty("state")
    private String state;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("role")
    private String role;
    @JsonProperty("landingurl")
    private String landingurl;
    @JsonProperty("resourcecode")
    private String resourcecode;
    private String classname;

    @JsonInclude(Include.NON_NULL)
    private List<ChildrenMenu> children;
}

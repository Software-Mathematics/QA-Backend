package com.commons.util.model.responses;

import com.commons.data.entity.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class HierarchalResponse {
    private Department parent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<HierarchalResponse> deptHierarchyResponse;
}

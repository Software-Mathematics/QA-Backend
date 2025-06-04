package com.commons.util.model.responses;

import lombok.Data;

import java.util.List;

@Data
public class ParentDeptHierarchyResponse {
    private List<DeptHierarchyResponse> deptHierarchyResponseList;
}

package com.commons.util.model.responses;

import com.commons.data.entity.Department;
import lombok.Data;

import java.util.List;

@Data
public class DeptHierarchyResponse {
    private Department department;
    private List<Department> deptHierarchyLists;
}

package com.commons.util.model.dto;

import lombok.Data;

@Data
public class EmployeeDTO extends BaseDtoI{
    private String empid;
    private String name;
    private String role;
    private String address;
    private  String classname ;

}

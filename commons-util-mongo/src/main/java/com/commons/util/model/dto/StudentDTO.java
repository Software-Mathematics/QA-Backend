package com.commons.util.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO extends BaseDtoI{

    private String profileid;
    private String resourcecode;
    private String firstname;
    private String lastname;
    private LocalDate dateofbirth;
    private String classname;
    private String rollno;
}

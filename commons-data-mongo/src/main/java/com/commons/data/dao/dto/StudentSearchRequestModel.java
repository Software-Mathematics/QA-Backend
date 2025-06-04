package com.commons.data.dao.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentSearchRequestModel {
    private Long id;
    private String profileId;
    private String resourceCode;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String className;
    private String rollNo;
}

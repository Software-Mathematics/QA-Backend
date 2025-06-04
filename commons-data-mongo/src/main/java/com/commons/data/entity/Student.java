package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "Student")
@Data
public class Student extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Student";

    @Id
    private Long id;
    private String profileid;
    private String resourcecode;
    private String firstname;
    private String lastname;
    private LocalDate dateofbirth;
    private String classname;
    private String rollno;
//    private boolean status;

}

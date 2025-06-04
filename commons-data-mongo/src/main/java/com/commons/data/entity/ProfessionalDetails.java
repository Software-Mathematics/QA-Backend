package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProfessionalDetails")
public class ProfessionalDetails extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "ProfessionalDetails";
    private  String classname ;
	@Id
    private Long id;
    private String jobtitle;
    private String orgname;
    private String type;
    private String deptcode;
    private String profileid;
    private String designationid;
    private String reporting;
    private String empid;

    private String dateofjoin;
    private String dateofleave;

    private String reasonofleaving;
    private String firstname;
    private String lastname;
    private String emailid;
    private String phoneno;

    public void setClassname(String classname) {
        this.classname = "ProfessionalDetails";
    }
}

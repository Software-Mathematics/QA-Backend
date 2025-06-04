package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ApprovalLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalLog extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ApprovalLog";
    private  String classname ;
    @Id
    private Long id;
    private String entityname;
    private String stage;
    private String entityreference;


    public void setClassname(String classname) {
        this.classname = "ApprovalFlow";
    }
}

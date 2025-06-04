package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ApprovalFlow")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApprovalFlow extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ApprovalFlow";
    private  String classname ;
    @Id
    private Long id;
    private String entityname;
    private String stage;
    private String stagetype;


    public void setClassname(String classname) {
        this.classname = "ApprovalFlow";
    }


}

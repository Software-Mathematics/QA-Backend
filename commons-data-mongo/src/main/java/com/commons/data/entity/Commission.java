package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Commission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commission  extends  BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Commission";
    private  String classname;
    @Id
    private Long id;
    private String profileid;
    private String agentcomissiontype;
    private String agentcomission;
    private String resourcecomissiontype;
    private String resourcecomission;
}

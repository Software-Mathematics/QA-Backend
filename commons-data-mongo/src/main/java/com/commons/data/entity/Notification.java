package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Notification";
    private  String classname;
    @Id
    private Long id;
    private String title;
    private String message;

    public void setClassname(String classname) {
        this.classname = "Notification";
    }


}
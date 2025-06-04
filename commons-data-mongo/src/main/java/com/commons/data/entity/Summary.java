package com.commons.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Summary")
public class Summary extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Summary";
    private  String classname ;
    @Id
    private Long id;
    private String name;
    private String experience;
    private String age;
    private String profileid;
    private String rescoursecode;
    private List<String> categorycodes;
    private List<Usercategory> usercategories;
    private String type;
    private String isactive;

    public void setClassname(String classname) {
        this.classname = "Summary";
    }

}

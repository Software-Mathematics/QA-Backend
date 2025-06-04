package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "ConsentMaster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsentMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "ConsentMaster";
    private  String classname;
    @Id
    private Long id;
    private String type;
    private String module_id;
    private String title;
    private String description;
    private String is_active;
    private String is_mandatory;
    private List<Map<String, String>> content;
    private String resourcecode;

    public void setClassname(String classname) {
        this.classname = "ConsentMaster";
    }

}

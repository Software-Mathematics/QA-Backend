package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Permission")
public class Permission extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "Permission";
    private  String classname ;
    @Id
    private Long id;
    private String code;
    private Map<String, Object> permission;

    public void setClassName(String className) {
        this.classname = "Permission";
    }
}

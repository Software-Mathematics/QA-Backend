package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "AssociationMaster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "AssociationMaster";
    private  String classname;
    @Id
    private Long id;
    private StationMaster station;
    private List<Category> checklist;
    private List<Category> pokayoke;
    private List<Machine> machine;

    public void setClassname(String classname) {
        this.classname = "AssociationMaster";
    }


}
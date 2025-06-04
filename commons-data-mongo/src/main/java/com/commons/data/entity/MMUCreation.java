package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "MMUCreation")
@Data
public class MMUCreation extends BaseEntity{

    @Transient
    public static final String SEQUENCE_NAME = "MMUCreation";
    @Id
    private Long id;
    private String name;
    private String description;
    private Address address;
    @Indexed(unique = true)
    private String code; // Auto-generation with Name
    private ItemMaster vehicle;
    private ItemMaster raspberry;
    private String type;
    private WareHouseMaster warehouse;
    private List<Equipment> equipmentlist;

    private  String classname ;

    public void setClassname(String classname) {
        this.classname = "MMUCreation";
    }

}

package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Itembatch")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Itembatch extends BaseEntity{
    @Transient

    public static final String SEQUENCE_NAME = "Itembatch";
    @Id
    private Long id;
    private String itemid;
    private String itemcode;
    private String quantity;
    private String consumed;
    private String batchno;
    private Date manufacturingdate;
    private Date expairydate;
    private String displayname;
    private String type;
    private String uniquecode;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "Itembatch";
    }

}

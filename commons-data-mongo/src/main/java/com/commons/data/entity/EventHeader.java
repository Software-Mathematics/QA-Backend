package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EventHeader")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventHeader extends BaseEntity {
    @Transient
    public static final String SEQUENCE_NAME = "EventHeader";
    private  String classname;
    @Id
    private Long id;
    private String slotsize;
    private String slotuom;
    private String averageusercount;
    private String profileid;
    private String code;
    private String mmrno;

    public void setClassname(String classname) {
        this.classname = "EventHeader";
    }

}


package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "CommunityEngagement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityEngagement extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "CommunityEngagement";
    private  String classname ;
    @Id
    private Long id;
    private String code;
    private String profilename;
    private String profileid;
    private Date datemmuvisit;
    private String activitytype;
    private String targetgroup;
    private String mmucode;
    private String village;
    private Integer totalparticipants;
    private Integer noofindividuals;
    private Integer noofhhs;
    private Integer totalduelist;
    private Integer registeredinopd;
    private Integer followupdone;
    private String remarks;

    public void setClassname(String classname) {
        this.classname = "CommunityEngagement";
    }


}

package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityEngagementDTO extends BaseDtoI {

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
    private String classname;
}

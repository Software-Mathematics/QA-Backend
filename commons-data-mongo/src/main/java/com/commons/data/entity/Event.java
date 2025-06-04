package com.commons.data.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "Event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Event";
    private  String classname;
    @Id
    private Long id;
    private String flag;
    private String code;
    private String title;
    private String requiredattendees;
    private String startdate;
    private String enddate;
    private String address;
    private String starttime;
    private String endtime;
    private String type;

    private Address addressobject;
    private String mmucode;
    private PinCode pincode;
    private String resourcecode;
    private String profileid;
    private String totalnoofattendees;
    private String averagetotalnoofattendees;
    private String actualnoofattendees;

    private String duration;
    private String durationuom;
    private String mmrno;
    private String slotsize;
    private String slotuom;
    private String averageusercount;
    private String bookingusercount;

    private String availabletime;
    private String planneddowntime;
    private String totaltime;
    private List<DownTime> down_time_list;


    public void setClassname(String classname) {
        this.classname = "Event";
    }

}


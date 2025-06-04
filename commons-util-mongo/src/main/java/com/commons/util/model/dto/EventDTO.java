package com.commons.util.model.dto;
import com.commons.data.entity.Address;
import com.commons.data.entity.DownTime;
import com.commons.data.entity.PinCode;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class EventDTO extends BaseDtoI {

//    private Long id;
    private String flag;
    private String code;
    private String title;
    private String requiredattendees;
    private String startdate;
    private String starttime;
    private String endtime;
    private String enddate;
    private String type;

    private String address;
    private Address addressobject;
    private String classname;
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
}

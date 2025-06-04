package com.commons.util.model.dto;


import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class ConsumerBaseResponse {
    private GeoJsonPoint location;
    private Long approveddate;
    private Long createddate;
    private Long modifieddate;
    private Long id;
    private String createdby;
    private String modifiedby;
    private String approver;
    private String approvertype;
    private String approvedby;
    private String latlong;
    private String recstatus;
    private String syncstatus;
    private String status;
    private String documentofproof;
    private String tempid;

}

package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.Date;
import java.util.List;


@Data
public class BaseEntity {

    private String createdby;
    private Date createddate;
    private String modifiedby;
    private Date modifieddate;
    private String approver;
    private String approvertype;
    private String approvedby;
    private Date approveddate;
    private String latlong;
    private String recstatus;
    private String recstatusname;
    private String subrecstatus;
    private String subrecstatusname;
    private String syncstatus;
    private String status;
    private String documentofproof;
    private String tempid;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    private String tanentid;
    private String mappingcode;
    private List<String> tags;
    private String createdbyname;
    private String modifiedbyname;
    private String assignedto;
    private String assignedtoname;
    private String mappingname;

}

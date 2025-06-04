package com.commons.util.model.dto;

import com.commons.data.deserializer.DateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.Date;
import java.util.List;

@Data
public abstract class BaseDtoI {
    private Long id;
    private String createdby;
//    @JsonDeserialize(using = DateDeserializer.class)
    private Date createddate;
    private String modifiedby;
//    @JsonDeserialize(using = DateDeserializer.class)
    private Date modifieddate;
	private String approver;
    private String approvertype;
	private String approvedby;
//    @JsonDeserialize(using = DateDeserializer.class)
	private Date approveddate;
	private String recstatus;
    private String recstatusname;
    private String subrecstatus;
    private String subrecstatusname;
	private String syncstatus;
    private String status;
    private String documentofproof;
    private GeoJsonPoint location;
    private String tempid;
    private String tanentid;
    private List<String> tags;
    private String mappingcode;
    private String createdbyname;
    private String modifiedbyname;
    private String assignedto;
    private String assignedtoname;
    private String mappingname;

}

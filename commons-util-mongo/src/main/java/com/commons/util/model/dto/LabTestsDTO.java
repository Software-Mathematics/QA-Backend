package com.commons.util.model.dto;

import com.commons.data.entity.Investigation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class LabTestsDTO extends BaseDtoI
{
	private String code;
	@JsonProperty("ptnumber")
	private String ptnumber;
@JsonProperty("testid")
private String testid;
	@JsonProperty("testname")
private String 	testname;
	@JsonProperty("testresult")
private String testresult;
	@JsonProperty("detailedresult")
private String detailedresult;
	@JsonProperty("presid")
private String presid;
	private String mmucode;
	private String classname;
	private String visitid;
	private List<Investigation> investigationlist;


}

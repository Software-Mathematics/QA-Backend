package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatutoryRecordsDTO extends BaseDtoI{

//	   private Long id;
	private String profileid;
	private String resourcecode;
	private String documentofproof;
	private String uannumber;
	private String pf;
	private String epsmember;
	private String pfnominee;
	private String esicnumber;
	private String esicnominee;
	private String lwfnumber;
	private String lefnominee;
	private String classname;


}

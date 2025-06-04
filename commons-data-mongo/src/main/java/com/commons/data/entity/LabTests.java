package com.commons.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.List;

@Document(collection= "LabTests") 
@Data
public class LabTests extends BaseEntity
{ 
	 @Transient
 public static final String SEQUENCE_NAME = "LabTests";
	@Id
	private Long id;

	private String code;
	private String ptnumber;
	private String testid;
    private String 	testname;
    private String testresult;
    private String detailedresult;
    private String presid;
    private String visitid;
	private String mmucode;
	private String classname;
	private List<Investigation> investigationlist;

	public void setClassname(String classname) {
		this.classname = "LabTests";
	}

    
	
	
	
	

}

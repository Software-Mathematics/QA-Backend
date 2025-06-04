package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Role")
public class Role extends BaseEntity {
	@Transient
	public static final String SEQUENCE_NAME = "Role";
	private  String classname ;
	@Id
	private Long id;
	private String name;
	private String rolecode;
	private String description;
	private String resourcecode;
	private String type;
	private Map<String, Object> permission;

	public void setClassName(String className) {
		this.classname = "Role";
	}
}

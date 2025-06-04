package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Datasets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datasets extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "Datasets";
    private  String classname;
    @Id
    private Long id;
    private Map<String, Object> request_body;
    private String desired_outcome;
    private String desired_status;
    private List<AcceptanceCriteria> acceptance_criteria;
    private Map<String, Object> params;
    private Authorization authorization;

    public void setClassname(String classname) {
        this.classname = "Datasets";
    }

}

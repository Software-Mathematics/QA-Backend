package com.softwaremathematics.api.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AcceptanceCriteria {
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    private String field;
    private String datatype;
    private String criteria;

    // Getters and Setters
}
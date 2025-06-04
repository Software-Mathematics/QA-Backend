package com.softwaremathematics.api.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScenarioMain {

    private String description;

    private String transaction_id;

    private String tanentid;

    private ProjectType type;

    private String value;

    private String report;

    private String progress;

    private RecStatus recstatus;

    private String processed_datasets;

    private String total_datasets;

    public String getProcessed_datasets() {
        return processed_datasets;
    }

    public void setProcessed_datasets(String processed_datasets) {
        this.processed_datasets = processed_datasets;
    }

    public String getTotal_datasets() {
        return total_datasets;
    }

    public void setTotal_datasets(String total_datasets) {
        this.total_datasets = total_datasets;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public RecStatus getRecstatus() {
        return recstatus;
    }

    public void setRecstatus(RecStatus recstatus) {
        this.recstatus = recstatus;
    }

    private List<DataList> data_list;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public List<DataList> getData_list() {
        return data_list;
    }

    public void setData_list(List<DataList> data_list) {
        this.data_list = data_list;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTanentid() {
        return tanentid;
    }

    public void setTanentid(String tanentid) {
        this.tanentid = tanentid;
    }

    @Override
    public String toString() {
        return "ScenarioMain{" +
                "description='" + description + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", report='" + report + '\'' +
                ", data_list=" + data_list +
                '}';
    }
}
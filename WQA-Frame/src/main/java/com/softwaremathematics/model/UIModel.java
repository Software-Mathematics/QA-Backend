package com.softwaremathematics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UIModel {

    private String feature_name;
    private Map<String, List<ActionDetail>> datasets;

    private String browser;

    private String email;

    private String password;

    private String subject;

    public UIModel() {
    }


    public UIModel(String feature_name, Map<String, List<ActionDetail>> datasets, String browser) {
        this.feature_name = feature_name;
        this.datasets = datasets;
        this.browser=browser;
    }

    public String getBrowser() {
        return browser;
    }


    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public Map<String, List<ActionDetail>> getDatasets() {
        return datasets;
    }

    public void setDatasets(Map<String, List<ActionDetail>> datasets) {
        this.datasets = datasets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

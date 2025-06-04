package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDataModel {

    private int srno;
    private String action;
    private String xpath;
    private String value;
    private String label;

}

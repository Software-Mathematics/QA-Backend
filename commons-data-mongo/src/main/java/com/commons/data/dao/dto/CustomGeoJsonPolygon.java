package com.commons.data.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomGeoJsonPolygon {
    private String type;
    private List<double[]> coordinates;
}

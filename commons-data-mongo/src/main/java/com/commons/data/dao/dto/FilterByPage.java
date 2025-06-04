package com.commons.data.dao.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FilterByPage {
    private Map<String, Object> searchItems;
    private List<double[]> polygonCoordinates;
    private NearLocation nearLocation;
    private String searchText;
    private List<String> searchOnFields;
    private CustomPageable pageable;
}

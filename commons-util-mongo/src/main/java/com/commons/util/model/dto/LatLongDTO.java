package com.commons.util.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class LatLongDTO {
    private String type;
    private List coordinates;
}

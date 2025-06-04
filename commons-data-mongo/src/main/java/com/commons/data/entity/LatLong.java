package com.commons.data.entity;

import lombok.Data;

import java.util.List;

@Data
public class LatLong {
    private String type;
    private List coordinates;
}

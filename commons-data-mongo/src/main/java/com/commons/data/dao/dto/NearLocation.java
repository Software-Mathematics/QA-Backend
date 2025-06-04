package com.commons.data.dao.dto;

import lombok.Data;

@Data
public class NearLocation {
    private double[] nearLocation;
    private double maxDistance;
}

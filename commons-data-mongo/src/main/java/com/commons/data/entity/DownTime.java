package com.commons.data.entity;

import lombok.Data;

@Data
public class DownTime {
    private String name;
    private String from;
    private String to;
    private String downtime;
}

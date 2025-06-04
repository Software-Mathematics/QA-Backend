package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "BOMCreation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BOMCreation extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "BOMCreation";
    @Id
    private Long id;
    private List<Item> items;
    private String modelno;
    private String stationname;
    private String name;
    private String description;
    private String pattern;
    private String processid;
}

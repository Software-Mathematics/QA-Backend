package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "VaccineMaster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineMaster extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "VaccineMaster";
    private  String classname;
    @Id
    private Long id;
    private String name;
    private VaccineMaster dependency;
    private String age_from;
    private String age_to;
    private String nature;
    private List<String> applicable;
    private List<String> brands;
    private String no_of_doses;
    private List<DoseGap> dose_gap_list;

    public void setClassname(String classname) {
        this.classname = "VaccineMaster";
    }

}
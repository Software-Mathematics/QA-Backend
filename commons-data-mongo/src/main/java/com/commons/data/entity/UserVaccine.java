package com.commons.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "UserVaccine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVaccine extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "UserVaccine";
    private  String classname;
    @Id
    private Long id;
    private String profile_id;
    private String username;
    private String user_age;
    private String due_date;
    private String batch_no;
    private String serial_no;
    private String brand;
    private String location_name;
    private String location_address;
    private String remarks;
    private String vaccine_id;
    private String name;
    private VaccineMaster dependency;
    private String age_from;
    private String age_to;
    private String nature;
    private List<String> applicable;
    private List<String> brands;
    private String no_of_doses;
    private List<DoseGap> dose_gap_list;
    private String taken_date;

    public void setClassname(String classname) {
        this.classname = "UserVaccine";
    }

}
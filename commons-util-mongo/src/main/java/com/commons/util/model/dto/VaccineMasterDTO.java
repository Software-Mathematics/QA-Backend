package com.commons.util.model.dto;

import com.commons.data.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineMasterDTO extends BaseDtoI {

    //    private Long id;
    private String name;
    private VaccineMaster dependency;
    private String age_from;
    private String age_to;
    private String nature;
    private List<String> applicable;
    private List<String> brands;
    private String no_of_doses;
    private List<DoseGap> dose_gap_list;

    private  String classname;

}
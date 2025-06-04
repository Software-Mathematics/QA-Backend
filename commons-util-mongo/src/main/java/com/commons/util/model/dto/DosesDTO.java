package com.commons.util.model.dto;

import com.commons.data.entity.Doses;
import com.commons.data.entity.Itembatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DosesDTO extends BaseDtoI{
    private String code;
    @JsonProperty("pdnumber")
    private String pdnumber;
    @JsonProperty("medicineid")
    private String medicineid;
    @JsonProperty("medicinename")
    private String medicinename;
    @JsonProperty("dosage")
    private String dosage;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("presid")
    private String presid;
    private String mmucode;
    private String quantity;
    private String classname;
    private String countperday;
    private String dose;
    @JsonProperty("uom")
    private String uom;
    private String visitid;
    private Doses previousdose;
    private List<Itembatch> itembatchlist;
    private Itembatch itembatch;
    private String form;

}

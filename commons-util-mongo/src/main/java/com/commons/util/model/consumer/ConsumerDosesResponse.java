package com.commons.util.model.consumer;

import com.commons.data.entity.Doses;
import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerDosesResponse extends ConsumerBaseResponse {
    private String code;
    private String pdnumber;
    private String medicineid;
    private String medicinename;
    private String dosage;
    private String frequency;
    private String duration;
    private String presid;
    private String mmucode;
    private String quantity;
    private String classname;
    private String countperday;
    private String dose;
    private String uom;
    private String visitid;
    private Doses previousdose;

}

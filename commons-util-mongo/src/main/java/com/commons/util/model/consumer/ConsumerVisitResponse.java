package com.commons.util.model.consumer;

import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

@Data
public class ConsumerVisitResponse extends ConsumerBaseResponse {
    private String visitid;
    private String patientvisitid;
    private String patientid;
    private String patienttempid;
    private String firstscreening;
    private String doctorid;
    private String mmucode;
    private String visittype;
    private String classname;
    private String visitcategory;
    private String followuptovisit;
    private String presid;
}

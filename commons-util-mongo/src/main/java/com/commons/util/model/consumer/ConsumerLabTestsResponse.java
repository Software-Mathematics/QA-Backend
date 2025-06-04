package com.commons.util.model.consumer;

import com.commons.data.entity.Investigation;
import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class ConsumerLabTestsResponse extends ConsumerBaseResponse {
    private String code;
    private String ptnumber;
    private String testid;
    private String testname;
    private String testresult;
    private String detailedresult;
    private String presid;
    private String mmucode;
    private String classname;
    private String visitid;
    private List<Investigation> investigationlist;

}

package com.commons.util.model.consumer;

import com.commons.data.entity.PinCode;
import com.commons.util.model.dto.ConsumerBaseResponse;
import lombok.Data;

import java.util.Date;

@Data
public class ConsumerEventResponse extends ConsumerBaseResponse {
    private Long id;
    private String code;
    private String title;
    private String requiredattendees;
    private Date startdate;
    private Date enddate;
    private String address;
    private String classname;
    private String mmucode;
    private PinCode pincode;
    private String resourcecode;
}

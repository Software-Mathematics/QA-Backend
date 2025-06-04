package com.commons.util.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplaintLogDTO extends BaseDtoI{
    private String ticket_no;
    private String remarks;
    private String status_name;
    private String entered_date_time;
    private String entered_by;

    public ComplaintLogDTO(String ticket_no, String remarks, String status, String entered_date_time, String entered_by) {
        this.ticket_no = ticket_no;
        this.remarks = remarks;
        this.status_name = status;
        this.entered_date_time = entered_date_time;
        this.entered_by = entered_by;
    }
}

package com.dailycodebuffer.filemngt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
public class AttachmentDTO {

    private String id;
    private String filename;
    private String filetype;
    private String doctype;
    private String uniquecode;
    private String profileid;

    private String username;
    private String resourcecode;
    private String name;
    private String description;
    private String code;
    private String downloadurl;
    private String model;


}

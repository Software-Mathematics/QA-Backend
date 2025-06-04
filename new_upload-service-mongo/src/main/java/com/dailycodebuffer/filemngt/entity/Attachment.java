package com.dailycodebuffer.filemngt.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attachment")
@Data@NoArgsConstructor
public class    Attachment {

    @Id
    private String id;

    private String filename;
    private String filetype;
    private String doctype;
    private String uniquecode;
    private String profileid;

    private String filepath;

//    @Lob
    private byte[] data;
    private String username;
    private String resourcecode;
    private String name;
    private String description;
    private String code;
    private String downloadurl;
    private String model;


    public Attachment(String fileName, String fileType, byte[] data, String userName, String resourceCode, String name, String description, String code, String downloadURL) {

        this.filename = fileName;
        this.filetype = fileType;
        this.data = data;
        this.username = userName;
        this.resourcecode = resourceCode;
        this.name = name;
        this.description = description;
        this.code = code;
        this.downloadurl = downloadURL;
    }

    public Attachment(String fileName, String fileType, byte[] data) {
        this.filename = fileName;
        this.filetype = fileType;
        this.data = data;
    }
}

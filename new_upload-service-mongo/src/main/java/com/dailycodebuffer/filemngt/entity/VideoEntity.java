package com.dailycodebuffer.filemngt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videoUpload")
public class VideoEntity {
    @Id
    private String id;
    private String doctorid;
    private String patientid;
    private String videoName;
    private Date addedDate;

    public VideoEntity(String id, String videoName, Date addedDate) {
        this.id = id;
        this.videoName = videoName;
        this.addedDate = addedDate;
    }
}

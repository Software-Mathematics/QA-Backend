package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RoomIdGeneration")
@Data
public class RoomIdGeneration  extends BaseEntity{
    @Transient
    public static final String SEQUENCE_NAME = "RoomIdGeneration";
    @Id
    private Long id;
    private String profileid;
    private String roomid;
    private String doctorid;
    private String link;
    private String bookingid;
}

package com.commons.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;


@Document(collection = "Otps")
@Data
@EntityScan
public class Otps extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "Otps";

    @Id
    private Long id;
    private String userid;
    private String otp;
    private String otptype;
    private String typevalue;
    private String resourcecode;
    private String resourcename;
    private LocalDateTime createdat;
    private LocalDateTime expiresat;
    private LocalDateTime confirmedat;
    private Map<String, Object> extra_fields;
    private String classname;

    public void setClassname(String classname) {
        this.classname = "Otps";
    }
}

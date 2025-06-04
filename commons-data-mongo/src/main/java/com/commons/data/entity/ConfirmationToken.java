package com.commons.data.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "token")
@Data
public class ConfirmationToken {
    @Id
    private String id;

//    @Column(name = "token")
    private String token;

//    @Column(name = "createdAt")
    private LocalDateTime createdat;

//    @Column(name = "expiresAt")
    private LocalDateTime expiresat;

//    @Column(name = "confirmedAt")
    private LocalDateTime confirmedat;

//    @OneToOne(cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Registration registration;
    private  String classname;
    public void setClassname(String classname) {
        this.classname = "ConfirmationToken";
    }
}
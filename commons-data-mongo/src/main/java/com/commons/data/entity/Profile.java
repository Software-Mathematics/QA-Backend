package com.commons.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Profile")
@Data
public class Profile {

    @Id
    private String id;

//    @OneToOne(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER
//    )
//    @JoinColumn(
//            name = "personalInformation_id",
//            referencedColumnName = "id"
//    )
    private PersonalInformation personalinformation;

}

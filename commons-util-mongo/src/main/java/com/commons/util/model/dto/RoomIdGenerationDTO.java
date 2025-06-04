package com.commons.util.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomIdGenerationDTO extends BaseDtoI{
    private String profileid;
    private String roomid;
    private String doctorid;
    private String link;
    private String bookingid;
}

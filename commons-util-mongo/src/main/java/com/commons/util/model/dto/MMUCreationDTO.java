package com.commons.util.model.dto;

import com.commons.data.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class MMUCreationDTO extends BaseDtoI {
    private String name;
    private String description;
    private Address address;
    private String code; // Auto-generation with Name
    private String classname;
    private ItemMaster vehicle;
    private ItemMaster raspberry;
    private WareHouseMaster warehouse;
    private List<Equipment> equipmentlist;
    private List<Price> price;
    private String type;
    private List<Event> slots;
}

package com.commons.util.model.responses;

import com.commons.util.model.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

public class AddressResponse {
    @JsonInclude(Include.NON_NULL)
    private AddressDTO addressDTO;
    @JsonInclude(Include.NON_NULL)
    private List<AddressDTO> addressDTOS;

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public List<AddressDTO> getAddressDTOS() {
        return addressDTOS;
    }

    public void setAddressDTOS(List<AddressDTO> addressDTOS) {
        this.addressDTOS = addressDTOS;
    }
}

package com.commons.util.model.dto;

import com.commons.data.entity.Address;
import com.commons.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegistrationDTO extends BaseDtoI{
    private String code;
    private String profileid;
    private Product product;
    private String dateofpurchase;
    private String purchasefromretailer;
    private String title;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String alternatephonenumber;
    private Address address;
    private String description;
    private String invoice;
    private String category;
    Map<String, Object> extradetail;
    private String classname;

}

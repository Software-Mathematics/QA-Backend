package com.commons.data.dao.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class CustomPageable {
    private Integer page;
    private Integer size;
    private String sortBy;           // Field to sort by
    private String sortDirection;    // ASC or DESC

    public Integer getPage() {
        return page != null ? page : 0;  // Return 0 if page is null
    }

    public Integer getSize() {
        return size != null ? size : 10;  // Return 10 if size is null
    }

    public Sort getSort() {
        Sort.Direction direction = Sort.Direction.ASC; // Default sorting direction
        if (sortDirection != null && sortDirection.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }
        return Sort.by(direction, sortBy != null && !sortBy.isEmpty() ? sortBy : "id"); // Default sorting field is "id"
    }
}

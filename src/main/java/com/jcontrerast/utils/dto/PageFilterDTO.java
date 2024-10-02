package com.jcontrerast.utils.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageFilterDTO {
    protected Short pageNumber;
    protected Short pageSize;
    protected String sortColumn;
    protected String sortDirection;

    public Sort.Direction getSortDirection() {
        return Sort.Direction.ASC.toString().equalsIgnoreCase(sortDirection)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
    }
}

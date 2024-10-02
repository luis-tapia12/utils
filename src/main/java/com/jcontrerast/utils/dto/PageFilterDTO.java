package com.jcontrerast.utils.dto;

import com.jcontrerast.utils.Constants;
import com.jcontrerast.utils.validation.NullableNotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageFilterDTO {
    @Min(0)
    @Max(Short.MAX_VALUE)
    protected Short pageNumber;
    @Min(Constants.MIN_PAGE_SIZE)
    @Max(Constants.MAX_PAGE_SIZE)
    protected Short pageSize;
    @NullableNotBlank
    protected String sortColumn;
    @NullableNotBlank
    protected String sortDirection;

    public Sort.Direction getSortDirection() {
        return Sort.Direction.ASC.toString().equalsIgnoreCase(sortDirection)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
    }
}

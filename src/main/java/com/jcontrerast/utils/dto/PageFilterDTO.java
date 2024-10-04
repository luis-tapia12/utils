package com.jcontrerast.utils.dto;

import com.jcontrerast.utils.Constants;
import com.jcontrerast.utils.validation.NullableNotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

/**
 * Data Transfer Object for pagination and sorting parameters.
 *
 * <p>This class encapsulates the details required for paginating a list of items,
 * including the page number, page size, sort column, and sort direction.
 * It provides validation constraints to ensure that the pagination parameters are
 * within acceptable ranges.</p>
 */
@Getter
@Setter
public class PageFilterDTO {
    /**
     * The page number to retrieve (zero-based).
     *
     * <p>This value must be a non-negative integer and cannot exceed
     * {@link Short#MAX_VALUE}.</p>
     */
    @Min(0)
    @Max(Short.MAX_VALUE)
    protected Short pageNumber;

    /**
     * The number of items per page.
     *
     * <p>This value must be within the range defined by
     * {@link Constants#MIN_PAGE_SIZE} and {@link Constants#MAX_PAGE_SIZE}.</p>
     */
    @Min(Constants.MIN_PAGE_SIZE)
    @Max(Constants.MAX_PAGE_SIZE)
    protected Short pageSize;

    /**
     * The name of the column to sort by.
     *
     * <p>This field is optional and must not be blank if provided.</p>
     */
    @NullableNotBlank
    protected String sortColumn;

    /**
     * The direction to sort the results.
     *
     * <p>This field is optional and must not be blank if provided. Valid values are
     * "ASC" for ascending and "DESC" for descending.</p>
     */
    @NullableNotBlank
    protected String sortDirection;

    public Sort.Direction getSortDirection() {
        return Sort.Direction.ASC.toString().equalsIgnoreCase(sortDirection)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
    }
}

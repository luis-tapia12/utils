package com.jcontrerast.utils;

import com.jcontrerast.utils.dto.PageFilterDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class Utils {
    public static Pageable getPageable(PageFilterDTO filter) {
        Assertions.isNotNull(filter);
        short pageNumber = Optional.ofNullable(filter.getPageNumber()).orElse(Constants.DEFAULT_PAGE_NUMBER);
        short pageSize = Optional.ofNullable(filter.getPageSize()).orElse(Constants.DEFAULT_PAGE_SIZE);

        Assertions.isPositive(pageNumber);
        Assertions.isLessOrEqualThan(pageNumber, Short.MAX_VALUE);
        Assertions.isLessOrEqualThan(pageSize, Constants.MAX_PAGE_SIZE);
        Assertions.isGreaterOrEqualThan(pageSize, Constants.MIN_PAGE_SIZE);

        if (filter.getSortColumn() != null) {
            Assertions.isNotNull(filter.getSortDirection());

            Sort sort = Sort.by(filter.getSortDirection(), filter.getSortColumn());

            return PageRequest.of(pageNumber, pageSize, sort);
        }

        return PageRequest.of(pageNumber, pageSize);
    }
}

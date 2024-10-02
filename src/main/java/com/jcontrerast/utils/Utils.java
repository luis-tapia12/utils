package com.jcontrerast.utils;

import com.jcontrerast.utils.dto.PageFilterDTO;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Optional;

public class Utils {
    public static Pageable getPageable(PageFilterDTO filter) {
        if (filter == null) {
            return PageRequest.of(Constants.DEFAULT_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
        }

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

    @SneakyThrows
    public static <T> void copyNotNull(T source, T target) {
        Assertions.isNotNull(source);
        Assertions.isNotNull(target);

        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            Object value = field.get(source);
            if (value != null) field.set(target, field.get(source));
        }
    }
}

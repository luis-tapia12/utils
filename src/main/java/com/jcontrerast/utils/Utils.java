package com.jcontrerast.utils;

import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.exception.AssertionException;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Optional;

public class Utils {
    /**
     * Creates a {@link Pageable} object based on the provided {@link PageFilterDTO}.
     *
     * <p>If the provided filter is null, a default {@link Pageable} is returned
     * with default page number and size. If the filter is not null, the method
     * extracts the page number, page size, and optional sorting information,
     * applying necessary validations.</p>
     *
     * <p>Validations include checking that the page number and size are positive,
     * within allowed ranges, and that if sorting is specified, both the sort
     * column and direction are provided.</p>
     *
     * @param filter the {@link PageFilterDTO} containing pagination and sorting parameters.
     * @return a {@link Pageable} object reflecting the specified or default pagination and sorting settings.
     * @throws AssertionException if the page number or size is invalid based on defined constraints.
     */
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

    /**
     * Copies non-null properties from the source object to the target object.
     *
     * <p>This method iterates over the fields of the source object and checks
     * each field for a non-null value. If a field's value is not null, that
     * value is copied to the corresponding field in the target object. Both
     * the source and target objects must be of the same type.</p>
     *
     * @param <T> the type of the objects being copied
     * @param source the source object from which non-null values are copied
     * @param target the target object to which non-null values are set
     * @throws AssertionException if either the source or target object is null
     */
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

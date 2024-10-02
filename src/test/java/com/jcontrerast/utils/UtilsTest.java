package com.jcontrerast.utils;

import com.jcontrerast.utils.dto.PageFilterDTO;
import com.jcontrerast.utils.exception.AssertionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.domain.Pageable;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilsTest {
    @ParameterizedTest
    @MethodSource
    void testGetPageable(
            Integer pageNumber,
            Integer pageSize,
            String sortColumn,
            String sortDirection
    ) {
        boolean isSorted = sortColumn != null;

        PageFilterDTO filter = new PageFilterDTO();
        filter.setPageNumber(pageNumber != null ? pageNumber.shortValue() : null);
        filter.setPageSize(pageSize != null ? pageSize.shortValue() : null);
        filter.setSortColumn(sortColumn);
        filter.setSortDirection(sortDirection);

        Pageable response = Utils.getPageable(filter);

        assertEquals(isSorted, response.getSort().isSorted());
        assertEquals(Objects.requireNonNullElse(pageNumber, Constants.DEFAULT_PAGE_NUMBER).intValue(), response.getPageNumber());
        assertEquals(Objects.requireNonNullElse(pageSize, Constants.DEFAULT_PAGE_SIZE).intValue(), response.getPageSize());
    }

    @ParameterizedTest
    @MethodSource
    void testGetPageable_whenInvalidFilter(
            Integer pageNumber,
            Integer pageSize,
            String sortColumn,
            String sortDirection
    ) {
        PageFilterDTO filter = new PageFilterDTO();
        filter.setPageNumber(pageNumber != null ? pageNumber.shortValue() : null);
        filter.setPageSize(pageSize != null ? pageSize.shortValue() : null);
        filter.setSortColumn(sortColumn);
        filter.setSortDirection(sortDirection);

        assertThrows(AssertionException.class, () -> Utils.getPageable(filter));
    }

    static Stream<Arguments> testGetPageable() {
        return Stream.of(
                Arguments.of(0, 10, "column1", "ASC"),
                Arguments.of(0, 10, "column1", "DESC"),
                Arguments.of(0, 10, "column1", "asc"),
                Arguments.of(0, 10, "column1", "desc"),
                Arguments.of((int) Short.MAX_VALUE, (int) Constants.MAX_PAGE_SIZE, null, null),
                Arguments.of(0, (int) Constants.MIN_PAGE_SIZE, null, null),
                Arguments.of(null, (int) Constants.MIN_PAGE_SIZE, null, null),
                Arguments.of(0, null, null, null)
        );
    }

    static Stream<Arguments> testGetPageable_whenInvalidFilter() {
        return Stream.of(
                Arguments.of(-10, 10, "column1", "ASC"),
                Arguments.of(Integer.MAX_VALUE, 10, "column1", "DESC"),
                Arguments.of(0, 0, "column1", "asc"),
                Arguments.of(0, Integer.MAX_VALUE, "column1", "desc")
        );
    }
}

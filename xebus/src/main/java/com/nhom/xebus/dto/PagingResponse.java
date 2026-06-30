package com.nhom.xebus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse<T> {

    private List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;

    public static <T> PagingResponse<T> of(
            List<T> content,
            int currentPage,
            int pageSize,
            long totalElements
    ) {
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        boolean first = currentPage == 0;
        boolean last = currentPage >= totalPages - 1;
        boolean hasNext = !last;
        boolean hasPrevious = !first;

        return new PagingResponse<>(
                content,
                currentPage,
                pageSize,
                totalElements,
                totalPages,
                first,
                last,
                hasNext,
                hasPrevious
        );
    }
}

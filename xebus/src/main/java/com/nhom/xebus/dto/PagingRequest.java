package com.nhom.xebus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingRequest {

    private int page = 0;
    private int size = 10;
    private String sortBy = "maTuyen";
    private String sortDir = "asc";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page >= 0 ? page : 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size > 0 ? size : 10;
    }
}

package com.fct.serp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {

    private int current = 1;
    private int pageSize = 20;
    private long totalPages = 0;
    private long totalRecords = 0;

    public PageInfo(int current, int pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }
}

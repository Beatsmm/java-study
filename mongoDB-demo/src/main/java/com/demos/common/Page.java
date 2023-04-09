package com.demos.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Page <T>{


    private Integer pageIndex;

    private Integer pageSize;

    private Integer total;

    private Integer pages;

    private List<T> data;

    public static <T> Page<T> emptyResult(){
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageSize(10);
        page.setTotal(0);
        page.setPages(0);
        page.setData(new ArrayList<>());
        return page;
    }

    public static <T> Page<T> getPageResult(List<T> list, Integer pageIndex, Integer pageSize, Integer countSize) {
        Page<T> page = new Page<>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotal(countSize);
        page.setPages(countSize % pageSize == 0 ? countSize / pageSize : countSize / pageSize + 1);
        page.setData(list);
        return page;
    }

}

package com.ld.web.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>Title: Page</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description: 分页实体类</p>
 *
 * @author LD
 * 
 * @param <T>
 *
 * @date 2015-1-8
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 6464430746475414041L;

    private int currentPage;

    private int pageSize;

    private Long total;

    private List<T> records;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Page() {
    }

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Page(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

}

package com.qiang.qianqiancater.bean;

import java.util.List;

/**
 * @author QIANG
 * 分页对象
 */
public class PageBean<T> {
    private int totalRecords; //总记录数
    private int totalPages;// 总页数
    private int pageSize; //每一页的大小
    private int currentPage;//当前页码
    
    private List<T> dataList;//这一页显示的数据集合

    /////getter and setter////////
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}

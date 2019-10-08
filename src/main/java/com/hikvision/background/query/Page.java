package com.hikvision.background.query;

import java.util.List;

/**
 * @ClassName Page
 * @Description 分页类
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class Page {

    public String pageNow = "1";
    public String pageSize = "10";
    public String pageCount;
    public String total;
    public String orderBy = "1 desc";
    public List resultList;

    public Page(String pageNow, String pageSize) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
    }

    public Page(String pageNow, String pageSize, String orderBy) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
        this.orderBy = orderBy;
    }

    public String getPageNow() {
        return pageNow;
    }

    public void setPageNow(String pageNow) {
        this.pageNow = pageNow;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
}

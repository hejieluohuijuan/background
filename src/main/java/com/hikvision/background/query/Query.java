package com.hikvision.background.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Query
 * @Description 查询类
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class Query {

    public Class clazz;

    public Page page;

    public List<Map<String, String>> andList = new ArrayList<>();

    public List<Map<String, String>> orList = new ArrayList<>();

    public Query(Class clazz) {
        this.clazz = clazz;
    }

    public void toQueryPage(Query query, String pageNow, String pageSize) {
        Page page = new Page(pageNow, pageSize);
        query.page = page;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void eq(String field, String value) {
        this.addList(field, value, "=", "and");
    }

    public void ne(String field, String value) {
        this.addList(field, value, "<>", "and");
    }

    public void lt(String field, String value) {
        this.addList(field, value, "<", "and");
    }

    public void gt(String field, String value) {
        this.addList(field, value, ">", "and");
    }

    public void lte(String field, String value) {
        this.addList(field, value, "<=", "and");
    }
    public void gte(String field, String value) {
        this.addList(field, value, ">=", "and");
    }

    public void like(String field, String value) {
        this.addList(field, value, "like", "and");
    }

    public void orEq(String field, String value) {
        this.addList(field, value, "=", "or");
    }

    public void orNe(String field, String value) {
        this.addList(field, value, "<>", "or");
    }

    public void orLt(String field, String value) {
        this.addList(field, value, "<", "or");
    }

    public void orGt(String field, String value) {
        this.addList(field, value, ">", "or");
    }

    public void orLte(String field, String value) {
        this.addList(field, value, "<=", "or");
    }
    public void orGte(String field, String value) {
        this.addList(field, value, ">=", "or");
    }

    public void orLike(String field, String value) {
        this.addList(field, value, "like", "or");
    }

    /**
     * 添加and条件
     * @param field
     * @param value
     * @param operator
     * @param type and/or
     */
    public void addList(String field, String value, String operator, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("field", field);
        map.put("value", value);
        map.put("operator", operator);
        if("and".equals(type)) {
            this.andList.add(map);
        } else {
            this.orList.add(map);
        }
    }

}

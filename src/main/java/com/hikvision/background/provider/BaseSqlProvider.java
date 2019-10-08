package com.hikvision.background.provider;


import com.hikvision.background.query.Page;
import com.hikvision.background.query.Query;
import com.hikvision.background.web.controller.common.TableAnnotation;
import com.hikvision.background.web.util.StringUtil;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName BaseSqlProvider
 * @Description 基础sql生成
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class BaseSqlProvider<T> {

    /**
     * 单个插入
     * @param t
     * @return
     */
    public String insertOne(T t) {
        SQL sql = new SQL();
        // 获取注解
        TableAnnotation tableAnnotation = t.getClass().getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();

        // 对象转为map
        HashMap<String, Object> map = (HashMap<String, Object>) t;
        Set<String> keys = map.keySet();

        sql.INSERT_INTO(tableName);
        for(String key : keys) {
            sql.VALUES(StringUtil.humpToLine(key), String.format("#{" + key + ",jdbcType=VARCHAR}"));
        }
        return sql.toString();
    }

    /**
     * 批量插入
     * @param map
     * @return
     */
    public String insertBatch(Map<String, Object> map) {
        List<T> list = (List<T>) map.get("list");
        if(CollectionUtils.isEmpty(list))
            return "";
        // 获取注解
        TableAnnotation tableAnnotation = list.get(0).getClass().getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();

        String insertSql = "insert into " + tableName + "(";
        // 对象转为map
        HashMap<String, Object> tMap = (HashMap<String, Object>) list.get(0);
        // 拼接column
        Set<String> keys = tMap.keySet();
        for(String key : keys) {
            insertSql += "`" + key +"`" + ",";
        }
        insertSql = insertSql.substring(0, insertSql.length()-1);
        insertSql += ") values";

        // 拼接values
        for(int i=0; i < list.size(); i++) {
            HashMap<String, Object> valueMap = (HashMap<String, Object>) list.get(i);
            insertSql += "(";
            for(String key : keys) {
                insertSql += "'" + valueMap.get(key)  +"'" + ",";
            }
            insertSql = insertSql.substring(0, insertSql.length()-1);
            insertSql+="),";
        }
        insertSql = insertSql.substring(0, insertSql.length()-1);
        return insertSql;
    }

    /**
     * 根据id更新
     * @param t
     * @return
     */
    public String updateById(T t) {
        SQL sql = new SQL();
        // 获取注解
        TableAnnotation tableAnnotation = t.getClass().getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();
        String pk = tableAnnotation.id();

        // 对象转为map
        HashMap<String, Object> map = (HashMap<String, Object>) t;
        Set<String> keys = map.keySet();

        sql.UPDATE(tableName);
        for(String key : keys) {
            if(key != StringUtil.lineToHump(pk)) {
                sql.SET(StringUtil.humpToLine(key) + "=" + String.format("#{" + key + ",jdbcType=VARCHAR}"));
            }
        }

        sql.WHERE(pk + "=" + String.format("#{" + StringUtil.lineToHump(pk) + ",jdbcType=VARCHAR}"));

        return sql.toString();
    }

    /**
     * 根据id删除
     * @param t
     * @return
     */
    public String deleteById(T t) {
        SQL sql = new SQL();
        // 获取注解
        TableAnnotation tableAnnotation = t.getClass().getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();
        String pk = tableAnnotation.id();

        // 拼接delete语句
        sql.DELETE_FROM(tableName);
        sql.WHERE(pk + "=" + String.format("#{" + StringUtil.lineToHump(pk) + ",jdbcType=VARCHAR}"));
        return sql.toString();
    }

    /**
     * 根据id查询
     * @param t
     * @return
     */
    public String selectById(T t) {
        SQL sql = new SQL();
        // 获取注解
        TableAnnotation tableAnnotation = t.getClass().getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();
        String pk = tableAnnotation.id();

        sql.SELECT("*");
        sql.FROM(tableName);
        sql.WHERE(pk + "=" + String.format("#{" + StringUtil.lineToHump(pk) + "}"));
        return sql.toString();
    }

    public String selectAllCount(Query query) {

        SQL sql = new SQL();
        Class clazz = query.clazz;
        // 获取注解
        TableAnnotation tableAnnotation = (TableAnnotation)clazz.getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();

        sql.SELECT("COUNT(1)");
        sql.FROM(tableName);
        List<Map<String, String>> andList = query.andList;
        List<Map<String, String>> orList = query.orList;

        // 拼接查询条件
        dealCondition(sql, andList, orList);

        return sql.toString();
    }

    /**
     * 根据条件查询
     * query key为字段的驼峰值  value 为  type_value
     * type eq lt gt lte gte ne like
     * @param query
     * @return
     */
    public String selectByQuery(Query query) {

        SQL sql = new SQL();
        Class clazz = query.clazz;
        // 获取注解
        TableAnnotation tableAnnotation = (TableAnnotation)clazz.getDeclaredAnnotation(TableAnnotation.class);
        String tableName = tableAnnotation.tableName();

        sql.SELECT("*");
        sql.FROM(tableName);
        List<Map<String, String>> andList = query.andList;
        List<Map<String, String>> orList = query.orList;

        // 拼接查询条件
        dealCondition(sql, andList, orList);

        String querySql = sql.toString();
        Page page = query.getPage();
        // 拼接orderby语句
        if(page != null) {
            querySql += "\n order by " + page.orderBy;
        }
        // 拼接limit 语句
        if(page != null) {
            querySql += "\n limit " + page.pageSize + " offset " + ((Integer.valueOf(page.pageNow)-1)*Integer.valueOf(page.pageSize));
        }

        return querySql;
    }

    /**
     * 处理查询条件
     * @param sql
     * @param andList
     * @param orList
     */
    private void dealCondition(SQL sql, List<Map<String, String>> andList, List<Map<String, String>> orList) {
        // 拼接and条件
        for(Map<String, String> andMap : andList) {
            dealWhere(sql, andMap);
        }

        // 拼接or条件
        if(!CollectionUtils.isEmpty(orList)) {
            if(!CollectionUtils.isEmpty(andList))
                sql.OR();
            for(int i=0; i< orList.size(); i++) {
                Map<String, String> orMap = orList.get(i);
                dealWhere(sql, orMap);
                if(i < orList.size()-1)
                    sql.OR();
            }
        }
    }

    private void dealWhere(SQL sql, Map<String, String> map) {

        String field = StringUtil.humpToLine(map.get("field"));
        String value = map.get("value");
        String operator = map.get("operator");
        if("like".equals(operator)) {
            sql.WHERE(field + " " + operator + " '%" + value + "%'");
        } else {
            sql.WHERE(field + " " + operator + " '" + value + "'");
        }
    }
}

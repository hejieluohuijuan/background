package com.hikvision.background.service.base;

import com.hikvision.background.query.Page;
import com.hikvision.background.query.Query;

import java.util.List;

/**
 * @ClassName BaseService
 * @Description 基础service
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public abstract interface BaseService<T> {

    public void insertOne(T t);

    public void insertBatch(List<T> list);

    public void updateById(T t);

    public void deleteById(T t);

    public T selectById(T t);

    public T selectOne(Query query);

    public int selectAllCount(Query query);

    public List<T> selectByQuery(Query query);

    public Page selectByQueryPage(Query query);
}

package com.hikvision.background.service.impl;

import com.hikvision.background.dao.mapper.BaseMapper;
import com.hikvision.background.query.Page;
import com.hikvision.background.query.Query;
import com.hikvision.background.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description baseService实现类
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper baseMapper;

    @Override
    public void insertOne(T t) {
        baseMapper.insertOne(t);
    }

    @Override
    public void insertBatch(List<T> list) {
        baseMapper.insertBatch(list);
    }

    @Override
    public void updateById(T t) {
        baseMapper.updateById(t);
    }

    @Override
    public void deleteById(T t) {
        baseMapper.deleteById(t);
    }

    @Override
    public T selectById(T t) {
        return (T)baseMapper.selectById(t);
    }

    @Override
    public T selectOne(Query query) {
        return (T)baseMapper.selectOne(query);
    }

    @Override
    public int selectAllCount(Query query) {
        return baseMapper.selectAllCount(query);
    }

    @Override
    public List<T> selectByQuery(Query query) {
        return baseMapper.selectByQuery(query);
    }

    @Override
    public Page selectByQueryPage(Query query) {
        Page page = query.getPage();
        List<T> list = baseMapper.selectByQuery(query);
        int count = baseMapper.selectAllCount(query);
        int pageSize = Integer.valueOf(page.pageSize);
        int pageCount = (count + pageSize)/pageSize;
        page.setResultList(list);
        page.setTotal(String.valueOf(count));
        page.setPageCount(String.valueOf(pageCount));
        return page;
    }

}

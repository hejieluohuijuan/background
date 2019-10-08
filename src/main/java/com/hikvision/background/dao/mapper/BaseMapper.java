package com.hikvision.background.dao.mapper;


import com.hikvision.background.kernel.BaseEntity;
import com.hikvision.background.provider.BaseSqlProvider;
import com.hikvision.background.query.Query;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName BaseMapper
 * @Description 基础mapper
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
@Mapper
public abstract interface BaseMapper<T> {

    @InsertProvider(type = BaseSqlProvider.class, method = "insertOne")
    @Options(useGeneratedKeys = true)
    public void insertOne(T t);

    @InsertProvider(type = BaseSqlProvider.class, method = "insertBatch")
    public void insertBatch(@Param("list") List<T> list);

    @UpdateProvider(type = BaseSqlProvider.class, method = "updateById")
    public void updateById(T t);

    @DeleteProvider(type = BaseSqlProvider.class, method = "deleteById")
    public void deleteById(T t);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectAllCount")
    @ResultType(Integer.class)
    public int selectAllCount(Query query);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectById")
    @ResultType(BaseEntity.class)
    public T selectById(T t);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectByQuery")
    @ResultType(BaseEntity.class)
    public T selectOne(Query query);

    @SelectProvider(type = BaseSqlProvider.class, method = "selectByQuery")
    @ResultType(BaseEntity.class)
    public List<T> selectByQuery(Query query);

}

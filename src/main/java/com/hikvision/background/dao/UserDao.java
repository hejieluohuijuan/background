package com.hikvision.background.dao;

import com.hikvision.background.kernel.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
@Mapper
public interface UserDao {
    SysUser findUserByAttribute(@Param("column") String attribute, @Param("value") String value);

    SysUser login(SysUser sysUser);
}

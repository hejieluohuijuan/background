package com.hikvision.background.service;

import com.hikvision.background.kerny.pojo.SysUser;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
public interface UserService {
    SysUser findUserByAttribute(String attribute, String value);

    SysUser login(SysUser sysUser);
}

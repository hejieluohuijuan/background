package com.hikvision.background.service.impl;

import com.hikvision.background.dao.UserDao;
import com.hikvision.background.kerny.pojo.SysUser;
import com.hikvision.background.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public SysUser findUserByAttribute(String attribute, String value) {
        return userDao.findUserByAttribute(attribute, value);
    }

    @Override
    public SysUser login(SysUser sysUser) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        SysUser user = userDao.login(sysUser);
        attr.getRequest().getSession().setAttribute("loginUser", user);
        return user;
    }
}

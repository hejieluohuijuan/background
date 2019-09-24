package com.hikvision.background.service;

import com.hikvision.background.kerny.pojo.SysUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
public interface UserService {
    SysUser findUserByAttribute(String attribute, String value);

    SysUser login(SysUser sysUser);

    boolean saveSevenDayUser(SysUser sysUser);

    void reviewRememberSevenDay(Model model);

    void delSevenDaoUser();
}

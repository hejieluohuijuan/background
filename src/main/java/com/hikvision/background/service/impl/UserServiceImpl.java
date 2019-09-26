package com.hikvision.background.service.impl;

import com.hikvision.background.dao.UserDao;
import com.hikvision.background.kerny.pojo.SysUser;
import com.hikvision.background.service.UserService;
import com.hikvision.background.web.controller.common.AttrConstants;
import com.hikvision.background.web.util.EncryptUtils;
import com.hikvision.background.web.util.MD5Tools;
import com.hikvision.background.web.util.SessionUtil.SessionUtil;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
        //Md5对密码进行加密匹配
        String strMD5Pwd = MD5Tools.getStrrMD5(sysUser.getPassword());
        sysUser.setPassword(strMD5Pwd);
        SysUser user = userDao.login(sysUser);
        attr.getRequest().getSession().setAttribute("loginUser", user);
        return user;
    }

    /**
     * 保存七天功能
     *
     * @param sysUser 用户登陆信息
     * @return
     */
    @Override
    public boolean saveSevenDayUser(SysUser sysUser) {
        //获取该用户的用户名和密码
        String userName = sysUser.getUserName();
        String password = sysUser.getPassword();
        //将该信息保存到cookie中
        try {
            Cookie userCookie = new Cookie(AttrConstants.USER_COOKIE, userName + "_" + password);
//        userCookie.setMaxAge(1*60*60*24*7);
            //测试值
            userCookie.setMaxAge(60 * 5);
            userCookie.setPath(SessionUtil.getRequest().getContextPath());
            SessionUtil.getResponse().addCookie(userCookie);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void delSevenDaoUser() {
        Cookie[] cookies = SessionUtil.getRequest().getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (AttrConstants.USER_COOKIE.equals(cookies[i].getName())) {
                Cookie delUserCookie = new Cookie(AttrConstants.USER_COOKIE, "");
                delUserCookie.setMaxAge(0);
                delUserCookie.setPath(SessionUtil.getRequest().getContextPath());
                SessionUtil.getResponse().addCookie(delUserCookie);
            }
        }
    }

    @Override
    public void reviewRememberSevenDay(Model model) {
        Cookie[] cookies = SessionUtil.getRequest().getCookies();
        //先将空值存入防止报错
        SysUser sysUser = new SysUser("", "");
        model.addAttribute(AttrConstants.USER_COOKIE, sysUser);
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (AttrConstants.USER_COOKIE.equals(cookies[i].getName())) {
                // 获取到了该用户存放的cookie
                String value = cookies[i].getValue();
                //将用户名和密码放到model中
                String[] namePwd = value.split("_");
                String userName = namePwd[0] == null ? "" : namePwd[0];
                String password = namePwd[1] == null ? "" : namePwd[1];
                sysUser = new SysUser(userName, password);
                model.addAttribute(AttrConstants.USER_COOKIE, sysUser);
            }
        }
    }
}

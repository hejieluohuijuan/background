package com.hikvision.background.web.controller;

import com.hikvision.background.kerny.pojo.SysUser;
import com.hikvision.background.service.UserService;
import com.hikvision.background.web.controller.common.AttrConstants;
import com.hikvision.background.web.controller.common.ForwardConstants;
import com.hikvision.background.web.controller.component.LoginHandlerInterceptor;
import com.hikvision.background.web.util.SessionUtil.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.*;


import javax.servlet.http.HttpSession;


/**
 * @author 何杰
 * @date 2019/09/11
 **/
@Controller
public class Login implements WebMvcConfigurer {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin() {
        //跳转到登录页面
//        return "redirect:" + ForwardConstants.LOGIN_HTML;
        return ForwardConstants.LOGIN;
    }

    @ResponseBody
    @PostMapping(value = "/user/login")
    public String login(HttpSession session) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(SessionUtil.getPara("userName"));
        sysUser.setPassword(SessionUtil.getPara("password"));
        SysUser user = userService.login(sysUser);
        if (user != null) {
//            return "redirect:/index.html";
            return AttrConstants.SUCCESS;
        }
//        return "login";
        return AttrConstants.ERROR;
    }

    //修改默认静态资源位置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截的请求，并排除几个不拦截的请求
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login", "/static/**", "/login", "/login.html");
    }
}

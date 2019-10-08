package com.hikvision.background.web.controller;

import com.hikvision.background.kernel.pojo.SysUser;
import com.hikvision.background.service.LoginService;
import com.hikvision.background.service.UserService;
import com.hikvision.background.web.controller.common.AttrConstants;
import com.hikvision.background.web.controller.common.ForwardConstants;
import com.hikvision.background.web.util.SessionUtil.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;


/**
 * @author 何杰
 * @date 2019/09/11
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Login /*implements WebMvcConfigurer*/ {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String toLogin(Model model) {
        //获取登录列表
        List<HashMap<String, Object>> hashMapsList = loginService.loginModelData();
        //回显记住我功能呢
        userService.reviewRememberSevenDay(model);
        model.addAttribute("modelLoginLists", hashMapsList);
        return modelName();
    }

    @ResponseBody
    @GetMapping("/login/modelName")
    public String modelName(){
        //跳转到登录页面根据后台的跳转页面
        List<HashMap<String, Object>> hashMaps = loginService.loginModel();
        String modelName = hashMaps.size() > 0 ? String.valueOf(hashMaps.get(0).get("modelName")) : ForwardConstants.LOGIN;
        return modelName;
    }
    @ResponseBody
    @PostMapping("/login/switchLogin")
    public String switchLogin() {
        String modelName = SessionUtil.getPara("modelName");
        loginService.editLoginModel(modelName);
        return modelName;
    }

    @ResponseBody
    @PostMapping(value = "/login/user/login")
    public String login() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(SessionUtil.getPara("userName"));
        sysUser.setPassword(SessionUtil.getPara("password"));
        SysUser user = userService.login(sysUser);
        //判断是否选择记住我七天免登录
        String storePwd = SessionUtil.getPara("storePwd");
        if (user != null) {
            //如果勾选的话七天面登录
            if ("true".equals(storePwd)) {
                //保存七天
                boolean save = userService.saveSevenDayUser(sysUser);
            } else {
                userService.delSevenDaoUser();
            }
            return AttrConstants.SUCCESS;
        }
        return AttrConstants.ERROR;
    }

  /*  //修改默认静态资源位置
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
                .excludePathPatterns("/index.html", "/", "/static/**", "/login/**", "/login.html", "/resourceData/*");
    }*/
}

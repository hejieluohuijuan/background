package com.hikvision.background;

import com.hikvision.background.kerny.pojo.SysUser;
import com.hikvision.background.service.LoginService;
import com.hikvision.background.service.ResourceService;
import com.hikvision.background.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackgroundApplicationTests {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(BackgroundApplicationTests.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ResourceService resourceService;

    @Test
    public void contextLoads() {
        SysUser id = userService.findUserByAttribute("USER_NAME", "1");
        logger.info("success");
        System.out.println(id);
    }
    @Test
    public void loginTest(){
        SysUser sysUser=new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword("123456");
        SysUser user=userService.login(sysUser);

    }
    @Test
    public void loginModel(){
        List<HashMap<String, Object>> hashMaps = loginService.loginModel();

    }
    @Test
    public void resourceData(){
        HashMap<String, Object> urlPathName = resourceService.getUrlPathName("LOGIN_HEAD_IMG", "DELETE_FLAG", "0");
    }
    @Test
    public void modelSwitch(){
        loginService.editLoginModel("/login_bak");
    }

}

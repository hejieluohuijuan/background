package com.hikvision.background;

import com.hikvision.background.kerny.pojo.SysUser;
import com.hikvision.background.service.LoginService;
import com.hikvision.background.service.ResourceService;
import com.hikvision.background.service.UserService;
import com.hikvision.background.service.hotel.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private HotelService hotelService;

    @Test
    public void contextLoads() {
        SysUser id = userService.findUserByAttribute("USER_NAME", "1");
        logger.info("success");
        System.out.println(id);
    }

    @Test
    public void loginTest() {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword("123456");
        SysUser user = userService.login(sysUser);

    }

    @Test
    public void loginModel() {
        List<HashMap<String, Object>> hashMaps = loginService.loginModel();

    }

    @Test
    public void resourceData() {
        HashMap<String, Object> urlPathName = resourceService.getUrlPathName("LOGIN_HEAD_IMG", "DELETE_FLAG", "0");
    }

    @Test
    public void modelSwitch() {
        loginService.editLoginModel("/login_bak");
    }

    @Test
    public void hotel() {
        Map<String, Object> map = new HashMap<>();
        map.put("hotelName", "名称");
        map.put("hotelLocation", "位置");
        map.put("hotelScore", 5);
        map.put("hotelRate", 6);
        map.put("hotelRebuildtimeStart", "2019-1-1 00:00:00");
        map.put("hotelRebuildtimeEnd", "2019-1-2 00:00:00");
        map.put("columnDim", "酒店");
        map.put("hotelFacility", "设备");
        List<Map<String, Object>> hotelByAttribute = hotelService.findHotelByAttribute(map);
        for (Map<String, Object> mapsingle : hotelByAttribute) {
            System.out.println(mapsingle.get("hotelName"));
        }
    }
}

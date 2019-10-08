package com.hikvision.background;

import com.hikvision.background.dao.mapper.HotelMapper;
import com.hikvision.background.kernel.HotelEntity;
import com.hikvision.background.kernel.pojo.SysUser;
import com.hikvision.background.query.Query;
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

import java.util.Date;
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
    @Autowired
    private HotelMapper hotelMapper;

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

    @Test
    public void deleHotelById() {
        int attfact = hotelService.delete("HOTEL_ID", "1001");
        System.out.println(attfact);
    }

    @Test
    public void addHotel() {
        Map<String, Object> map = new HashMap<>();
        map.put("hotelName", "七天连锁酒店");
        map.put("hotelLocation", "浙江省杭州市江干区东亚新干线");
        map.put("hotelScore", 5.0);
        map.put("hotelRate", 6);
        map.put("mainFacility", "主要设施");
        map.put("hotelService", "设施服务");
        map.put("hotelFacility", "旅馆设备");
        map.put("roomFacility", "房间设施");
    }

    @Test
    public void hotelBaseTest() {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.put("id", "1");
        Query query = new Query(HotelEntity.class);
        query.getPage();
        List<HotelEntity> hotelEntities = hotelMapper.selectByQuery(query);
        System.out.println(hotelEntities);
    }
    @Test
    public void addHotelBase(){
        HotelEntity hotelEntity=new HotelEntity();
        hotelEntity.put("hotelName", "七天连锁酒店");
        hotelEntity.put("hotelId", "1003");
        hotelEntity.put("hotelLocation", "浙江省杭州市江干区东亚新干线");
        hotelEntity.put("hotelScore", 5.0);
        hotelEntity.put("hotelRate", 6);
        hotelEntity.put("mainFacility", "主要设施");
        hotelEntity.put("hotelService", "设施服务");
        hotelEntity.put("hotelFacility", "旅馆设备");
        hotelEntity.put("roomFacility", "房间设施");
        hotelMapper.insertOne(hotelEntity);
    }
    @Test
    public void updateHotelBase(){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.put("hotelName", "圣庭酒店");
        hotelEntity.put("hotelId", "1003");
        Date date = new Date();
        hotelEntity.put("hotelIntime", "14:00:00");
        hotelEntity.put("hotelOuttime", "12:00:00");
        hotelEntity.put("hotelImg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1570532158974&di=3444922723cdd39f04699a4a767907ef&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ftuangou%2F184%2F276%2F860%2Fcdc5013dfa4247f3aec7b7dad36c03d1_720_480_s.jpg");
        hotelEntity.put("id", "2");
        hotelMapper.updateById(hotelEntity);
    }
    @Test
    public void delHotelBase(){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.put("id", "3");
        hotelMapper.deleteById(hotelEntity);
    }
}

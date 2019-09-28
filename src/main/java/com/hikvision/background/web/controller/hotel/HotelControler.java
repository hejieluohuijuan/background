package com.hikvision.background.web.controller.hotel;

import com.hikvision.background.dao.hotel.HotelDao;
import com.hikvision.background.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: hejie
 * @date: 2019/9/27 20:34
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
@RestController
@RequestMapping("/hotel/")
public class HotelControler {
    @Autowired
    private HotelService hotelService;

    /**
     * 通过条件查询所有
     * @param map
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @GetMapping("findAllByAttribute")
    private List<Map<String, Object>> findAllByAttribute(@RequestParam  Map<String, Object> map) {
        //将该map中的值放到搜索中
        return hotelService.findHotelByAttribute(map);
    }
}

package com.hikvision.background.web.controller.hotel;

import com.hikvision.background.dao.hotel.HotelDao;
import com.hikvision.background.dao.mapper.HotelMapper;
import com.hikvision.background.kernel.HotelEntity;
import com.hikvision.background.service.hotel.HotelService;
import com.hikvision.background.web.controller.common.AttrConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * @Author: hejie
 * @date: 2019/9/27 20:34
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
@RestController
@RequestMapping("/hotel/")
@CrossOrigin
public class HotelControler {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelMapper hotelMapper;

    /**
     * 通过条件查询所有
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("findAllByAttribute")
    private List<Map<String, Object>> findAllByAttribute(@RequestParam Map<String, Object> map) {
        //将该map中的值放到搜索中
        return hotelService.findHotelByAttribute(map);
    }

    /**
     * 通过id更新
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("updateHotel")
    public String updateHotel(@RequestBody HashMap<String,Object> map) {
        try {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            HotelEntity hotelEntity = new HotelEntity();
            while (iterator.hasNext()){
                Map.Entry<String, Object> next = iterator.next();
                hotelEntity.put(next.getKey(),next.getValue());
            }
            hotelMapper.updateById(hotelEntity);
        } catch (Exception e) {
            System.out.println(e);
            return AttrConstants.ERROR;
        }
        return AttrConstants.SUCCESS;
    }

}

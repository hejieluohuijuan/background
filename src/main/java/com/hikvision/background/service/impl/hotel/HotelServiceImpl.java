package com.hikvision.background.service.impl.hotel;

import com.hikvision.background.dao.hotel.HotelDao;
import com.hikvision.background.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hejie
 * @date: 2019/9/27 20:35
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;

    @Override
    public HashMap<String, Object> hotelAllOrById(String hotelId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findHotelByAttribute(Map<String, Object> map) {
        return hotelDao.findHotelByAttribute(map);
    }
}

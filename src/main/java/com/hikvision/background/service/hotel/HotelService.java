package com.hikvision.background.service.hotel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: hejie
 * @date: 2019/9/27 20:34
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
public interface HotelService {
    HashMap<String,Object> hotelAllOrById(String hotelId);

    List<Map<String, Object>> findHotelByAttribute(Map<String, Object> map);

    int delete(String column, String value);

}

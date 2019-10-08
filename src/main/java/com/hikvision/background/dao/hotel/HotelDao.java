package com.hikvision.background.dao.hotel;

import java.util.List;
import java.util.Map;

/**
 * @Author: hejie
 * @date: 2019/9/27 20:38
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
public interface HotelDao {
    List<Map<String, Object>> findHotelByAttribute(Map<String, Object> map);

    int updateByAttribute(String column, String value,String whereColumn,String whereValue);

    int addHotel(Map<String, Object> map);
}

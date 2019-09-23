package com.hikvision.background.dao;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: hejie
 * @date: 2019/9/23 20:09
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
public interface LoginDao {
    List<HashMap<String,Object>> loginModel();
    List<HashMap<String,Object>> loginModelData();
}

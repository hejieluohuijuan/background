package com.hikvision.background.service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: hejie
 * @date: 2019/9/23 20:06
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
public interface LoginService {
    List<HashMap<String,Object>> loginModel();
    List<HashMap<String,Object>> loginModelData();
}

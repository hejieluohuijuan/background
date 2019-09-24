package com.hikvision.background.service;

import java.util.HashMap;

/**
 * @author 何杰
 * @date 2019/09/24
 **/
public interface ResourceService {
    HashMap<String,Object> getUrlPathName(String tableName,String column,String value);
}

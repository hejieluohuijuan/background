package com.hikvision.background.dao;

import java.util.HashMap;

/**
 * @author 何杰
 * @date 2019/09/24
 **/
public interface ResourceDataDao {
    HashMap<String,Object> getUrlPathName(String tableName, String column, String value);
}

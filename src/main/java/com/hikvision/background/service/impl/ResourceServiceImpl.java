package com.hikvision.background.service.impl;

import com.hikvision.background.dao.ResourceDataDao;
import com.hikvision.background.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author 何杰
 * @date 2019/09/24
 **/
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceDataDao resourceDataDao;
    @Override
    public HashMap<String, Object> getUrlPathName(String tableName, String column, String value) {
        return resourceDataDao.getUrlPathName(tableName, column, value);
    }
}

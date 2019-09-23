package com.hikvision.background.service.impl;

import com.hikvision.background.dao.LoginDao;
import com.hikvision.background.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: hejie
 * @date: 2019/9/23 20:08
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public List<HashMap<String, Object>> loginModel() {
        return loginDao.loginModel();
    }
    @Override
    public List<HashMap<String, Object>> loginModelData() {
        return loginDao.loginModelData();
    }
}

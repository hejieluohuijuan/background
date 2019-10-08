package com.hikvision.background.kernel;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName BaseEntity
 * @Description 基础实体
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class BaseEntity extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getString(String key) {
        return get(key) == null ? "" : String.valueOf(get(key));
    }

    public double getDouble(String key) {
        return get(key) == null ? 0.0 : Double.valueOf(getString(key));
    }

    public int getInt(String key) {
        return get(key) == null ? 0 : Integer.valueOf(getString(key));
    }

}

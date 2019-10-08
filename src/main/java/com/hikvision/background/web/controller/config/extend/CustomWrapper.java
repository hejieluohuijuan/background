package com.hikvision.background.web.controller.config.extend;

import com.hikvision.background.web.util.StringUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @ClassName CustomWrapper
 * @Description 自定义Wrapper
 * @Author gxy
 * @Date 2019/9/21 20:52
 * CopyRight 2019 gxy.All rights reserved.
 */
public class CustomWrapper extends MapWrapper {

    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }


    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if(useCamelCaseMapping){
            return StringUtil.lineToHump(name);
        }
        return name;
    }
}

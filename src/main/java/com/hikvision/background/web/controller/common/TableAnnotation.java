package com.hikvision.background.web.controller.common;

import java.lang.annotation.*;

/**
 * @Author: hejie
 * @date: 2019/10/8 14:45
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableAnnotation {
    String tableName();

    String id();
}

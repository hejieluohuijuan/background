package com.hikvision.background.web.util.SessionUtil;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
public class SessionUtil {

    public static String getPara(String param) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String value = request.getParameter(param);
        return value;

    }

    /**
     * 获取response
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req;
    }
}

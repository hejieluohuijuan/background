package com.hikvision.background.web.controller;

import com.hikvision.background.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author 何杰
 * @date 2019/09/24
 **/
@Controller
@RequestMapping("/resourceData/")
public class ResourceDataControl {
    @Autowired
    private ResourceService resourceService;

    @ResponseBody
    @GetMapping("headImg")
    public String headImg() {
        HashMap<String, Object> urlPathName = resourceService.getUrlPathName("LOGIN_HEAD_IMG", "DELETE_FLAG", "0");
        Object imgPath = urlPathName.get("imgPath");
        return imgPath == null ? "" : String.valueOf(imgPath);
    }
}

package com.hikvision.background.kernel.pojo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author 何杰
 * @date 2019/09/12
 **/
@Data
@ToString
public class SysUser {
    private String userName;
    private String name;
    private String password;
    private String deleteFlag;
    private Integer id;
    private Integer createUser;
    private Integer updateUser;
    private Date createTime;
    private Date updateTime;

    public SysUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public SysUser() {
    }
}

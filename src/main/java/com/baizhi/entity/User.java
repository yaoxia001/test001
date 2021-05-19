package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author 姚天下
 */

@Data
@Accessors(chain=true)
public class User {
    private String id;  //int 类型也可以，但是String 的API更多，方便后期方法调用
    private String username;
    private String realname;
    private String password;
    private String sex;
    private String status;
    private Date registerTime;


}

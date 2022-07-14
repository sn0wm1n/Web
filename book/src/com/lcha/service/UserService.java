package com.lcha.service;

import com.lcha.pojo.User;

/**
 * 根据servlet中的功能进一步使用DAO接口提供更为具体的可以直接使用的工具接口
 */
public interface UserService {
    //注册
    public void registUser(User user);
    //登录
    public User login(User user);
    //判断是否重复
    public boolean existsUsername(String username);
}

package com.lcha.dao;

import com.lcha.pojo.User;

/**
 * 对外提供的整个DAO包的功能
 *
 */
public interface UserDao {
    /**
     * 根据用户名查询用户类
     * @param username
     * @return 返回null说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码返回User类
     * @param username
     * @param password
     * @return 返回null说明没有这个用户
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 存储User到数据库
     * @param user
     */
    public int saveUser(User user);
}

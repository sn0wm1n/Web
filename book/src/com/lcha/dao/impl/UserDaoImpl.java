package com.lcha.dao.impl;

import com.lcha.dao.UserDao;
import com.lcha.pojo.User;
import org.junit.Test;

/**
 * 根据接口需求
 * 实现了针对用到的数据库的特定增删改查功能
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql="select * from t_user where username=?";
        return QueryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql="select * from t_user where username=? and password=?";
        return QueryForOne(User.class,sql,username,password);
    }
    /**
     * 存储信息到数据库
     * @param user 类信息
     * @return
     */
    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(username,password,email)values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

}

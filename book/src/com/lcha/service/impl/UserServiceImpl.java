package com.lcha.service.impl;

import com.lcha.dao.UserDao;
import com.lcha.dao.impl.UserDaoImpl;
import com.lcha.pojo.User;
import com.lcha.service.UserService;

/**
 * 根据servlet中的功能进一步使用DAO接口提供更为具体的可以直接使用的工具接口的实现
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }else return true;
    }
}

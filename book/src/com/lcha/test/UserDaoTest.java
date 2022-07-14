package com.lcha.test;

import com.lcha.dao.UserDao;
import com.lcha.dao.impl.UserDaoImpl;
import com.lcha.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("name0"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("name0", "1111"));
    }

    @Test
    public void saveUser() {

        System.out.println(userDao.saveUser(new User(null, "name1", "pass", "emal")));
    }
}
package com.lcha.test;

import com.lcha.pojo.User;
import com.lcha.service.UserService;
import com.lcha.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService us = new UserServiceImpl();

    @Test
    public void registUser() {
        us.registUser(new User(null, "王汉桑", "loverj", null));
    }

    @Test
    public void login() {
        System.out.println(us.login(new User(null, "王汉桑", "loverj", null)));
    }

    @Test
    public void existsUsername() {
        System.out.println(us.existsUsername("王汉桑"));
    }
}
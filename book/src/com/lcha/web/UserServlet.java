package com.lcha.web;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.lcha.pojo.User;
import com.lcha.service.UserService;
import com.lcha.service.impl.UserServiceImpl;
import com.lcha.utils.WebUtils;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_CONFIG_KEY;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService us = new UserServiceImpl();

    /**
     * 注销
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过返回值和login方法直接获取到了完整的User对象
        //正式使用了login
        System.out.println("loginUser.getUsername()"+"准备登录");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = us.login(new User(null,username,password,null));

        if (loginUser == null) {
            //用于回显
            request.setAttribute("username", username);
            //request.setAttribute("password", password);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            System.out.println("登陆失败");
        } else {
            request.getSession().setAttribute("user", loginUser);
            System.out.println(loginUser.getUsername()+"登录成功");
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);

        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String reqwd = request.getParameter("repwd");
        String email = request.getParameter("email");
        System.out.println(username+password+code+reqwd+email);
        if (token != null && token.equalsIgnoreCase(code)) {
            if (!us.existsUsername(username)) {
                User user = new User(null, username, password, email);
                us.registUser(user);
                RequestDispatcher rd = request.getRequestDispatcher("/pages/user/regist_success.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("用户名已经存在");
                request.setAttribute("msg", "用户名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        }else{
            System.out.println("验证码错误");
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

        }





    }
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得数据，返回数据
        String username = request.getParameter("username");
        System.out.println("ajax传回想注册的用户名为"+username);
        boolean existsUsername = us.existsUsername(username);
        if(existsUsername){
            System.out.println("ajax验证用户名重复了");
        }else{
            System.out.println("ajax验证用户名可用");
        }
        //封装成map返回
        Map<String,Boolean> resultMap = new HashMap<String,Boolean>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    @Test
    public void test() {
        String action = "hi";
        try {
            Method method = UserServlet.class.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //不用重载这里用到的就是原来的参数所以反射后面不写参数
            method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

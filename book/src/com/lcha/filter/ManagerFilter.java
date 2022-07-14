package com.lcha.filter;

import com.lcha.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行了管理员身份拦截");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        System.out.println(user);
        System.out.println("来登陆了");
        if(user==null||!user.getUsername().equals("王汉桑")){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);

        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}

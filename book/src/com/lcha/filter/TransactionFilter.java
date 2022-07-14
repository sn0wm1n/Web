package com.lcha.filter;

import com.lcha.utils.JDBCUtils;
import com.mysql.jdbc.JDBC4CallableStatement;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat统一管理展示友好的界面
        }
    }

    @Override
    public void destroy() {

    }
}

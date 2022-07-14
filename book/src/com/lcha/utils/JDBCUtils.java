package com.lcha.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

     static{
        try {
            Properties properties= new Properties();
            InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
         Connection conn = conns.get();
        try {
            if(conn==null){
                conn= dataSource.getConnection();
                conns.set(conn);//保存到ThreadLocal对象中，供后面jdbc操作使用
                conn.setAutoCommit(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
//        DruidPooledConnection conn = null;
//        try {
//            conn = dataSource.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return conn;
    }

    /**
     * 提交事务并且释放连接
     */
    public static void commitAndClose(){
        Connection conn=conns.get();
        if (conn!=null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.commit();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();//必须执行remove操作，否则就会出错，因为Tomcat底层使用了线程池技术
    }
    /**
     * 回滚事务并且释放连接
     */
    public static void rollbackAndClose(){
        Connection conn=conns.get();
        if (conn!=null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.rollback();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();//必须执行remove操作，否则就会出错，因为Tomcat底层使用了线程池技术
    }




//    public static void close(Connection conn){
//        if(conn!=null)
//        {
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
}

package com.lcha.dao.impl;

import com.lcha.pojo.User;
import com.lcha.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.management.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 借着QueryRunner和JDBCUtils类中的数据库连接池
 * 实现了通用的数据库增删改查功能
 */
public abstract class BaseDao {

//    public User qbook(){
//
//    }
    private QueryRunner queryRunner=new QueryRunner();
    //通用增删改

    /**
     *
     * @param sql
     * @param args
     * @return 更改的行数
     */
    public int update(String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
//        finally {
//            JDBCUtils.close(conn);
//        }//连接只能在事务提交的时候关闭
        //return -1;
    }
    //通用查询一条数据
    public <T> T QueryForOne(Class<T> type,String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        ResultSetHandler<T> rs = new BeanHandler<T>(type);
        try {
            return queryRunner.query(conn,sql,rs,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
//        finally {
//            JDBCUtils.close(conn);
//        }//连接只能在事务提交的时候关闭
        //return null;
    }
    public <T> List<T> QueryForList(Class<T> type,String sql,Object ...args)
    {
        Connection conn = JDBCUtils.getConnection();
        ResultSetHandler<T> rs = (ResultSetHandler<T>) new BeanListHandler<T>(type);
        try {
            return (List<T>) queryRunner.query(conn,sql,rs,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
//        finally {
//            JDBCUtils.close(conn);
//        }//连接只能在事务提交的时候关闭
        //return null;
    }
    public Object QueryForValue(String sql,Object ...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
//        finally {
//            JDBCUtils.close(conn);
//        }//连接只能在事务提交的时候关闭
    //return null;
    }

}
















//
//
//    public int update(String sql,Object ...args){
//        Connection conn = JDBCUtils.getConnection();
//        try {
//            return queryRunner.update(conn,sql,args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return -1;
//    }
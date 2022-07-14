package com.lcha.dao.impl;

import com.lcha.dao.OrderDao;
import com.lcha.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    @Test
    public void test(){
        Order order = new Order("123123",new Date(),new BigDecimal(100),0,1);
        saveOrder(order);
    }
}

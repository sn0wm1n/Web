package com.lcha.dao.impl;

import com.lcha.dao.OrderItemDao;
import com.lcha.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_Price`,`order_id`) values(?,?,?,?,?);";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
    @Test
    public void test(){
        OrderItem orderItem = new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(1),"123123");
        saveOrderItem(orderItem);
    }
}

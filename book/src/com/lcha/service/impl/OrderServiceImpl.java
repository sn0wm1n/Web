package com.lcha.service.impl;

import com.lcha.dao.BookDao;
import com.lcha.dao.OrderDao;
import com.lcha.dao.OrderItemDao;
import com.lcha.dao.impl.BookDaoImpl;
import com.lcha.dao.impl.OrderDaoImpl;
import com.lcha.dao.impl.OrderItemDaoImpl;
import com.lcha.pojo.*;
import com.lcha.service.OrderService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao= new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

        //int i = 12/0;

        for(Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet())
        {
            CartItem value = entry.getValue();
            OrderItem orderItem=
                    new OrderItem(null,value.getName(),value.getCount(),
                            value.getPrice(),value.getTotalPrice(),order.getOrderId());
            orderItemDao.saveOrderItem(orderItem);
            //改新库存和销量
            Book book = bookDao.queryBookById(value.getId());
            book.setSales(book.getSales()+value.getCount());
            book.setStock(book.getStock()- value.getCount());
            bookDao.updateBook(book);

        }
        cart.clean();
        return orderId;
    }
    @Test
    public void test(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart);
        OrderService orderService = new OrderServiceImpl();
        orderService.createOrder(cart,1);
    }
}

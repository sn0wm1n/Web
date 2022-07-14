package com.lcha.test;

import com.lcha.pojo.Cart;
import com.lcha.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {

        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart);

    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(3);
        System.out.println(cart);
    }

    @Test
    public void clean() {
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(3);
        cart.clean();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.deleteItem(3);
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}
package com.lcha.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
   // private Integer totalCount;
   // private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new LinkedHashMap<Integer, CartItem>();

    /**
     * 添加商品项
     */
    public void addItem(CartItem cartItem) {
        //h获取当前购物车中id相同的书本对象
        CartItem cartItem1 = items.get(cartItem.getId());
        if (cartItem1 == null) {
            //这个商品没添加过
            items.put(cartItem.getId(), cartItem);
        } else {
            //这个商品已经添加过
            cartItem1.setCount(cartItem1.getCount() + 1);
            cartItem1.setTotalPrice(cartItem1.getPrice().multiply(BigDecimal.valueOf(cartItem1.getCount())));
        }
    }

    /**
     * 删除商品项
     */
    public void deleteItem(Integer id) {
        items.remove(id);

    }

    /**
     * 清空购物车
     */
    public void clean() {
        items.clear();

    }

    /**
     * 修改商品数量
     */
    public void updateCount(Integer id, Integer out) {
        if (items.get(id) != null) {
            CartItem cartItem = items.get(id);
            {
                if (out == 0) items.remove(id);

                else {
                    // TODO: 2021/6/3 加了一个判空
                    cartItem.setCount(out);
                    cartItem.setTotalPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getCount())));
                }
            }
        }

    }

    public Cart() {
    }

    public Cart( Map<Integer, CartItem> items) {
        //this.totalCount = totalCount;
        //this.totalPrice = totalPrice;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem>entry:items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : items.values()) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return  totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}

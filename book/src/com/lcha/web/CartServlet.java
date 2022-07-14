package com.lcha.web;

import com.google.gson.Gson;
import com.lcha.pojo.Book;
import com.lcha.pojo.Cart;
import com.lcha.pojo.CartItem;
import com.lcha.service.BookService;
import com.lcha.service.impl.BookServiceImpl;
import com.lcha.utils.WebUtils;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---正式使用了addItem");
        //获取id
        Integer id = WebUtils.parseInt(request.getParameter("id"), -1);
        // 2根据ID获得图书信息
        Book book = bookService.queryBookById(id);
        // 3 根据图书信息封装商品信息
        CartItem cartItem
                = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 4 Cart.addItem(CartItem);添加商品项
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
       // System.out.println("添加之前"+request.getSession().getAttribute("cart"));
        cart.addItem(cartItem);
        // 5 重定向回到商品列表
       //System.out.println("添加之后"+request.getSession().getAttribute("cart"));
        //String referer = request.getHeader("Referer");
        //request.getSession().setAttribute("lastName",cartItem.getName());//最后一件添加的值
        String lastName = cartItem.getName();
        String count = String.valueOf(cart.getTotalCount());
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("lastName",lastName);
        resultMap.put("count",count);
        Gson gson=  new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }

    /**
     * 从购物车移除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---正式使用了deleteItem");
        //获取id
        Integer id = WebUtils.parseInt(request.getParameter("id"), -1);

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.deleteItem(id);

        // 5 重定向回到购物车列表
        // System.out.println("添加之后"+request.getSession().getAttribute("cart"));
        String referer = request.getHeader("Referer");

        response.sendRedirect(referer);
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---正式使用了clean清空购物车");


        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null)
        {cart.clean();}

        // 5 重定向回到购物车列表
        // System.out.println("添加之后"+request.getSession().getAttribute("cart"));

        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    /**
     * 更新商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---正式使用了updateItem更新商品数量");


        Cart cart = (Cart) request.getSession().getAttribute("cart");
//        if(cart!=null)
//        {cart.getItems()}
        Integer id = WebUtils.parseInt(request.getParameter("id"),-1);
        Integer out = WebUtils.parseInt(request.getParameter("count"),-1);
        if(cart!=null) {
            cart.updateCount(id, out);
        }
        //cart.getTotalPrice();
        // 5 重定向回到购物车列表
        // System.out.println("添加之后"+request.getSession().getAttribute("cart"));
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    @Test
    public void test(){
        Book book = new Book();
        book.setId(222);
        Book book2=book;
        book2.setId(211);
        System.out.println(book.getId());

    }
}


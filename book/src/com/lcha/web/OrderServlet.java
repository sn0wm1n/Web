package com.lcha.web;

import com.lcha.pojo.Cart;
import com.lcha.pojo.User;
import com.lcha.service.OrderService;
import com.lcha.service.impl.OrderServiceImpl;
import com.lcha.utils.JDBCUtils;
import com.lcha.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();
    /**
     * 创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("createOrder创建订单正式执行了");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser= (User) req.getSession().getAttribute("user");
        System.out.println("订单是"+loginUser+"的");
        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer id = loginUser.getId();

        String orderId = orderService.createOrder(cart, id);

        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}

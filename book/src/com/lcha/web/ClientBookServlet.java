package com.lcha.web;

import com.lcha.pojo.Book;
import com.lcha.pojo.Page;
import com.lcha.service.BookService;
import com.lcha.service.impl.BookServiceImpl;
import com.lcha.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("经过了前台分页bookClient");
        //前端获得的所有page相关的信息就这些
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        request.setAttribute("page", page);
        System.out.println(page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("经过了前台铵价格分页bookClient");
        //前端获得的所有page相关的信息就这些
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //最大价格最小价格
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        //调用bookService
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, max, min);

        //判断是否正在某个区间
        StringBuilder Url = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (request.getParameter("min") != null) {
            Url.append("&max=");
            Url.append(request.getParameter("max"));
            Url.append("&min=");

            Url.append(request.getParameter("min"));
        }
        page.setUrl(Url.toString());
        request.setAttribute("page", page);
        System.out.println(page);
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);

    }
}

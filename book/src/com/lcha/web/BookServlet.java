package com.lcha.web;

import com.lcha.pojo.Book;
import com.lcha.pojo.Page;
import com.lcha.service.BookService;
import com.lcha.service.UserService;
import com.lcha.service.impl.BookServiceImpl;
import com.lcha.utils.WebUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页的功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("----正式使用了后台分页");
        //前端获得的所有page相关的信息就这些
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"),Page.PAGE_SIZE);
        Page<Book> page=bookService.page(pageNo,pageSize);
        request.setAttribute("page",page);
        page.setUrl("manager/bookServlet?action=page");
        System.out.println("后台解析送走的page是"+page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取jsp数据封装成对象
        Book book = WebUtils.copyParatoBean(request.getParameterMap(), new Book());

        //添加
        bookService.addBook(book);
        //request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+Integer.MAX_VALUE);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bookService.deleteBookById(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = new Book();
        WebUtils.copyParatoBean(request.getParameterMap(),book);
        System.out.println("要被修改成的book是"+book);
        bookService.updateBook(book);
        System.out.println("修改的页面是"+request.getParameter("pageNo"));
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void getBean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("从前端获取到要修改的id是" + id);
        System.out.println("获取到的对应页码是"+request.getParameter("pageNo"));
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        request.setAttribute("book", book);
        System.out.println(book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过service层获得数据库数据

        List<Book> books = bookService.queryBooks();
        //存储到request域中，
        request.setAttribute("books", books);
        // 请求转发给jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}








//分页分析
// 每页多少行pageSize,每页从哪一行开始，这个一般就能显示所有的信息了，
//但是我们还需要给他带来一些能操作的工具栏
//首页，尾页，下一页，上一页，跳转到第n页，总页数，总记录条数，当前页数，跳转按钮
//功能性的先不看，先看需要获取值得需要的是
// 总页数pageTotal 总记录条数pageTotalCount 当前页数pageNO 当前页面显示的数据List<Book> items;
// 刚好就是Book类的所有属性了（pageSize固定）
//当前页数pageNo从请求中获得，总记录条数count*语句底层获得，总页数根据总记录条数/pageSize+if(%)
//当前页的数据通过当前页号 limit begin end语句sql底层获得
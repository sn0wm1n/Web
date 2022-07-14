package com.lcha.service.impl;

import com.lcha.dao.BookDao;
import com.lcha.dao.impl.BookDaoImpl;
import com.lcha.pojo.Book;
import com.lcha.pojo.Page;
import com.lcha.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page();
        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页面大小
        page.setPageSize(pageSize);
        //求book总数//设置book总数
        Integer pageTotalCount= bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求页面总数//设置页面总数
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize!=0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //边界条件判断
        if(pageNo<1){
            page.setPageNo(1);
        }
        if(pageNo>pageTotal){
            page.setPageNo(pageTotal);
        }


        //求页面信息//设置页面信息
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book>items=bookDao.queryForPageItems(begin,pageSize);

        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int max, int min) {
        Page<Book> page = new Page<Book>();
        //设置页幅和当前页面
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //求并且设置价格区间内book总数
        Integer pageTotalCount= bookDao.queryForPageTotalCount(max,min);
        page.setPageTotalCount(pageTotalCount);
        //求并设置价格区间内页面总数
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotalCount%pageSize!=0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //边界条件判断
        if(pageNo<1){
            page.setPageNo(1);
        }
        if(pageNo>pageTotal){
            page.setPageNo(pageTotal);
        }
        //求页面信息//设置页面信息
        int begin = (page.getPageNo()-1)*pageSize;
        List<Book>items=bookDao.queryForPageItems(begin,pageSize,max,min);
        page.setItems(items);
        return page;
    }

}

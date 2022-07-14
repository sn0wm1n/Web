package com.lcha.service;

import com.lcha.pojo.Book;
import com.lcha.pojo.Page;

import java.util.List;

public interface BookService {

    public void addBook(Book book);
    public void deleteBookById(Integer id);

    /**
     * 根据book类中的id找到book类并且修改
     * @param book
     */
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int max, int min);
}

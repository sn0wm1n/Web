package com.lcha.dao;

import com.lcha.pojo.Book;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 根据加个区间查询
     * @param max
     * @param min
     * @return
     */
    Integer queryForPageTotalCount(int max, int min);

    /**
     * 根据价格区间查询
     * @param begin
     * @param pageSize
     * @param max 最高价格
     * @param min 最低价格
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize, int max, int min);
}

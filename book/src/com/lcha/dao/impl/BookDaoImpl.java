package com.lcha.dao.impl;

import com.lcha.dao.BookDao;
import com.lcha.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(name,author,price,sales,stock,img_path)" +
                "values(?,?,?,?,?,?);";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());

    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {

        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book where id=?";
        return QueryForOne(Book.class,sql,id);
        //return null;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book";
        return QueryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        Number number = (Number) QueryForValue(sql);
        int count = number.intValue();
        return count;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
    String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        List<Book> books = QueryForList(Book.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount(int max, int min) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number number = (Number) QueryForValue(sql,min,max);
        int count = number.intValue();
        return count;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, int max, int min) {
        String sql = "select id,name,author,price,sales,stock,img_path " +
                "from t_book where price between ? and ? order by price limit ?,?";
        List<Book> books = QueryForList(Book.class, sql, min,max,begin, pageSize);
        return books;
    }
}

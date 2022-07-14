package com.lcha.test;

import com.lcha.dao.BookDao;
import com.lcha.dao.impl.BookDaoImpl;
import com.lcha.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
BookDao bookDao =new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"金光闪闪","老哈",new BigDecimal(998.98),1000000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(22,"银光闪闪","老哈",new BigDecimal(998.98),1000000,1,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(8, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void RqueryForPageTotalCount() {
        Integer integer = bookDao.queryForPageTotalCount(10, 9);
        System.out.println(integer);
//        String sql="select count(*) from t_book where price between min and max";
//        Number number = (Number) QueryForValue(sql);
//        int count = number.intValue();
//        return count;
    }

    @Test
    public void RqueryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 4, 10, 9);
        for (Book book : books) {
            System.out.println(book);
        }
//        String sql = "select id,name,author,price,sales,stock,img_path " +
//                "from t_book where price between min max limit ?,?";
//        List<Book> books = QueryForList(Book.class, sql, begin, pageSize);
//        return books;
    }
}
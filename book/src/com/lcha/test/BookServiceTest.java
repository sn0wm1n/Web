package com.lcha.test;

import com.lcha.pojo.Book;
import com.lcha.pojo.Page;
import com.lcha.service.BookService;
import com.lcha.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        Book book = new Book(null,"王汉桑","King",
                new BigDecimal(8848),123,123,null);
        bookService.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(24);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"王汉桑","King",
                new BigDecimal(8848),0,0,null));

    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }
    @Test
    public void test(){
        Page<Book> page = bookService.page(1, 4);
        List<Book> items = page.getItems();
        items.forEach(System.out::println);
    }
    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(1, 4, 50, 10);
        List<Book> items = page.getItems();
        for (Book item : items) {
            System.out.println(item);
        }
    }
}
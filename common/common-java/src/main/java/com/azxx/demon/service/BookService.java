package com.azxx.demon.service;

import com.azxx.demon.dao.mybatis.BookDao;
import com.azxx.demon.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao booksDao;

    public List<Book> getBooks(){
        return booksDao.getAllBooks();
    }


    public BookService() {
        System.out.println("enter the init BookServicer!");
    }

    public Book getBookById(int id){
        return booksDao.getBookById(id);
    }

    public int add(Book entity) throws Exception {
        if(entity.getTitle()==null||entity.getTitle().equals("")){
            throw new Exception("书名必须不为空");
        }
        return booksDao.add(entity);
    }

    @Transactional
    public int add(Book entity1,Book entityBak){
        int rows=0;
        rows=booksDao.add(entity1);
        rows=booksDao.add(entityBak);
        return rows;
    }

    public int delete(int id) {
        return booksDao.delete(id);
    }

    /**
     * 多删除
     */
    public int delete(String[] ids){
        int rows=0;
        for (String idStr : ids) {
            int id=Integer.parseInt(idStr);
            rows+=delete(id);
        }
        return rows;
    }

    public int update(Book entity) {
        return booksDao.update(entity);
    }
}

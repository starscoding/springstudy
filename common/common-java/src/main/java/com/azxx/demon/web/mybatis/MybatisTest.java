package com.azxx.demon.web.mybatis;

import com.azxx.demon.service.BookService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mybatistest")
public class MybatisTest {

    private static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/getAllBooks")
    public String getAllBooks(){
        logger.info(">>>>>>>>>>>>>");
        Gson gson = new Gson();
        return gson.toJson(bookService.getBooks());
    }

    public MybatisTest() {
        System.out.println("init mybatistest controller!");
    }
}

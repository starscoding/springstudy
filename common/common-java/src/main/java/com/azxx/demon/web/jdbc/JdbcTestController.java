package com.azxx.demon.web.jdbc;

import com.azxx.demon.entity.User;
import com.azxx.demon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("jdbctest")
public class JdbcTestController {

    private static Logger logger = Logger.getLogger(JdbcTestController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("insert")
    public String testInsert(@RequestParam String name,@RequestParam(required = true) int age){
        logger.info("enter the insert method!");
        User user = new User(name,age);
        userService.insert(user);
        return "插入成功！";
    }

    @GetMapping("testUpdate")
    public String testUpdate(@RequestParam String name,@RequestParam(required = true) int id){
        logger.info("enter the insert method!");
        User user = new User(name,id);
        userService.insert(user);
        return "更新成功！";
    }
    @GetMapping("testTxManager")
    public String testTxManager(){
        try {
            userService.testTxManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "方法结束";
    }
}

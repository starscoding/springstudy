package com.azxx.demon.service;

import com.azxx.demon.dao.hibernate.UserHibernateDao;
import com.azxx.demon.dao.jdbc.UserDao;
import com.azxx.demon.entity.User;
import com.azxx.demon.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserHibernateDao userHibernateDao;

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) throws Exception {
        userDao.update(user);
    }

    public void testTxManager() throws Exception {
        User user = new User("abc",14);
        this.insert(user);
        if(true) {
            throw new RuntimeException("手动抛出异常！");
        }
        user.setAge(22);
        this.update(user);
    }

    public void insertHibernate(UserInfoEntity user){
        userHibernateDao.insert(user);
    }
}

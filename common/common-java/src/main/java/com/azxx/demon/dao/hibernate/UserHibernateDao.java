package com.azxx.demon.dao.hibernate;

import com.azxx.demon.entity.UserInfoEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserHibernateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void insert(UserInfoEntity user) {
        this.sessionFactory.getCurrentSession().save(user);
    }
}

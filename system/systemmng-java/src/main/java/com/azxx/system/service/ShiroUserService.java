package com.azxx.system.service;

import com.azxx.system.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ShiroUserService {

    public List<String> getRoles(String userName){
        return null;
    }

    public Set<String> getResources(List<String> roles){
        return null;
    }

    public User getUser(String userName){
        return null;
    }
}

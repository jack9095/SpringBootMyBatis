package com.example.demo.service;

import com.example.demo.bean.Person;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    public Person Sel(int id){
        return userMapper.Sel(id);
    }

}

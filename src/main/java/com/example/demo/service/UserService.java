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

    public int Del(int id){
        return userMapper.Del(id);
    }

    public int Ins(Person person){
        return userMapper.Ins(person);
    }

    public int upDate(Person person){
        return userMapper.upDate(person);
    }

}

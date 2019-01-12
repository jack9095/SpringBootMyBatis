package com.example.demo.mapper;

import com.example.demo.bean.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    Person Sel(int id);
}

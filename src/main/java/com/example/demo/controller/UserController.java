package com.example.demo.controller;

import com.example.demo.bean.Person;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id) {
        return userService.Sel(id).toString();
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        int del = userService.Del(id);
        if (del == 1) {
            return "删除成功了  " + del;
        } else {
            return "删除失败了  " + del;
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insertInto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        int personId = Integer.parseInt(request.getParameter("id"));

        Person person = new Person();
        person.setId(personId);
        person.setUserName(request.getParameter("userName"));
        person.setRealName(request.getParameter("realName"));
        person.setPassWord(request.getParameter("passWord"));

        int ins = userService.Ins(person);

        if (ins == 1) { // 成功
            response.getWriter().write("成功增加了  " + ins);
            response.getWriter().close();
        }else{
            response.getWriter().write("增加失败  " + ins);
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void upData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        Person person = new Person();
        person.setId(id);
        person.setUserName(userName);
        int i = userService.upDate(person);
        if (i == 1) { // 成功
            response.getWriter().write("成功更新 "  + i);
        } else if (i == 0) { // 失败
            response.getWriter().write("更新失败 "  + i);
        }
        response.getWriter().close();
    }

}

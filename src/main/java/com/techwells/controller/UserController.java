package com.techwells.controller;

import com.techwells.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping("/user/{id}")
    public Object get(@PathVariable("id") Integer userId,HttpSession session){
        session.setAttribute("userName","陈加兵");
        try {
            System.out.println("chenjiabing");
            return userService.getUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }


    @RequestMapping("/user/getSession")
    public Object getSession(HttpSession session){
        return session.getAttribute("userName");
    }
}

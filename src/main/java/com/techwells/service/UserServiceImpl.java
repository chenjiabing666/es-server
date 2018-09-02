package com.techwells.service;

import com.techwells.beans.User;
import com.techwells.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service   //注入实例
public class UserServiceImpl implements  UserService{
    @Resource
    private UserDao userDao;

    @Override
    public Object addUser(User user) throws Exception {
        userDao.insertUser(user);
//        System.out.println(10/0);
        return null;

    }

    @Override
    public Object getUser(Integer userId) throws Exception {
        return userDao.selectUser(userId);
    }
}

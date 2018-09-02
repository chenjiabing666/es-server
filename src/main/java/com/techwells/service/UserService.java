package com.techwells.service;

import com.techwells.beans.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional   //开启事务的注解
public interface UserService {

    Object addUser(User user)throws  Exception;

    Object getUser(Integer userId)throws Exception;

}

package com.techwells.dao;

import com.techwells.beans.User;

/**
 * 用户的dao
 */
public interface UserDao {

    User selectUser(Integer userId);

    int insertUser(User user);

}

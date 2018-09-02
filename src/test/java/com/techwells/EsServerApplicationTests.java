package com.techwells;

import com.techwells.beans.User;
import com.techwells.dao.UserDao;
import com.techwells.es.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsServerApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void contextLoads() {
        User user=new User();
        user.setUserName("郑元梅");
        user.setBirthday(new Date());
        userDao.insertUser(user);

    }








}

package com.techwells;

import com.techwells.beans.User;
import com.techwells.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;


    @Test
    public void test1(){
//        String url=""

    }
}

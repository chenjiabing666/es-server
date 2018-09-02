package com.techwells;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement   //开启事务功能
@SpringBootApplication
@MapperScan(value ="com.techwells.dao")   //批量扫描mybatis的接口
public class EsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsServerApplication.class, args);
    }
}

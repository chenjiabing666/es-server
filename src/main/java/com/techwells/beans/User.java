package com.techwells.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "ea",type = "user",shards = 3,replicas = 0)
public class User {
    @Id
    private Integer userId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",fielddata = true)
    private String userName;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",fielddata = true)
    private String address;

    @Field(type = FieldType.Keyword)
    private String password;

    @Field(type = FieldType.Date)
    private Date birthday;

    private String userIcon;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", userIcon='" + userIcon + '\'' +
                ", age=" + age +
                '}';
    }
}

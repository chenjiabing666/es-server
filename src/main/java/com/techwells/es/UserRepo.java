package com.techwells.es;


import com.techwells.beans.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;


public interface UserRepo extends ElasticsearchRepository<User,Integer>{

    /**
     * 根据userId获取用户信息
     * @param userId
     * @return
     */
    User findUserByUserId(Integer userId);

    /**
     * 根据用户查找用户信息
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);



    /**
     * 根据用户名和密码查找用户信息，使用的是must查询
     * 参数的顺序不能颠倒
     * @param userName
     * @param password
     * @return
     */
    List<User> findByUserNameAndPassword(String userName,String password);


    /**
     * 根据用户名或者地址进行查询，满足其一即可，使用的是should
     * 参数不能颠倒
     * @param userName
     * @param address
     * @return
     */
    List<User> findByUserNameOrAddress(String userName,String address);


    /**
     * 使用@Query注解自定义查询语句，其中的?是占位符，0表示第一个参数
     * @param userName
     * @return
     */
    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"must\": [\n" +
            "        {\n" +
            "          \"match\": {\n" +
            "            \"userName\": \"?0\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    List<User> selectByUserName(String userName);


    /**
     * 查询密码不为null的用户信息
     * @return
     */
    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"must\":{\n" +
            "        \"exists\":{\n" +
            "          \"field\":\"password\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }")
    List<User> findByPasswordIsNotNull();


    /**
     * 查询密码为null的用户信息
     * @return
     */
    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"must_not\":{\n" +
            "        \"exists\":{\n" +
            "          \"field\":\"password\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }")
    List<User> findByPasswordIsNull();


    /**
     * 查询密码不是password的用户信息，使用的must_not
     * @param password
     * @return
     */
    List<User> findByPasswordNot(String password);

    /**
     * 查询用户名是userName但是密码表示password的信息，必须同时满足
     * @param userName
     * @param password
     * @return
     */
    List<User> findByUserNameAndPasswordNot(String userName,String password);


    /**
     * 查询年龄在from-to之间的用户，包含form和to，使用的是range查询
     * @param from  起始
     * @param to    截止
     * @return
     */
    List<User> findByAgeBetween(Integer from,Integer to);


    /**
     * 查询年龄小于age的用户信息
     * @param age  年龄
     * @return
     */
    List<User> findByAgeLessThan(Integer age);


    /**
     * 年龄小于等于age的用户信息
     */
    List<User> findByAgeLessThanEqual(Integer age);

    /**
     * 年龄大于age的用户
     * @param age
     * @return
     */
    List<User> findByAgeGreaterThan(Integer age);

    /**
     * 年龄大于等于age的用户
     * @param age
     * @return
     */
    List<User> findByAgeGreaterThanEqual(Integer age);

    /**
     * 年龄小于等于age的用户信息
     * @param age
     * @return
     */
    List<User> findByAgeBefore(Integer age);


    /**
     * 年龄大于等于age的用户
     * @param age
     * @return
     */
    List<User> findByAgeAfter(Integer age);


    /**
     * 模糊查找，密码中以pwd开头用户信息，`content%`，
     * @param content
     * @return
     */
    List<User> findByPasswordLike(String content);

    /**
     * 查询密码中包含content的用户信息  %content%
     * @param content
     * @return
     */
    List<User> findByPasswordContaining(String content);

    /**
     * 查询密码以pwd开头的用户信息，和Like一样的效果
     * @param pwd
     * @return
     */
    List<User> findByPasswordStartingWith(String pwd);


    /**
     * 查询密码以pwd结尾的用户信息
     * @param pwd
     * @return
     */
    List<User> findByPasswordEndingWith(String pwd);

    /**
     * 查找年龄在集合中的用户信息
     * @param ages
     * @return
     */
    List<User> findByAgeIn(List<Integer> ages);

    /**
     * 查找年龄不在集合中的用户信息
     * @param ages
     * @return
     */
    List<User> findByAgeNotIn(List<Integer> ages);


    /**
     * 根据用户名查询并且按照年龄降序排列
     * @param userName
     * @return
     */
    List<User> findByUserNameOrderByAgeDesc(String userName);

    /**
     * 根据用户名查询并且按照年龄降序排列、用户名升序排列
     * @param userName
     * @return
     */
    List<User> findByUserNameOrderByAgeDescUserNameAsc(String userName);


    /**
     * 根据出生日期进行降序排列
     * @param userName
     * @return
     */
    List<User> findByUserNameOrderByBirthdayDesc(String userName);

    /**
     * 返回前2条数据
     * @param userName
     * @return
     */
    List<User> findTop2ByUserName(String userName);

    /**
     * 根据用户名分页查询
     * @param userName
     * @param pageable
     * @return
     */
    Page<User> findByUserName(String userName, Pageable pageable);
}

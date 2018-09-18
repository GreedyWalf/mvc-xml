package com.qs.service.mapper;

import com.qs.entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 获取所有用户记录
     */
    List<User> getAllUsers();

    /**
     * 根据userId获取用户信息（一个简单类型的参数，mapper.xml中使用#{value}任意字符串匹配参数）
     */
    User getUserByUserId(String userId);

    /**
     * 根据userId和userName获取用户记录（使用@param注解指定参数别名，在mapper.xml中使用#{userId},#{userName}对应参数userId和userName）
     */
//    List<User> getUserListByUserIdAndUserName(@Param("userId") String userId, @Param("userName") String userName);


    /**
     * 根据userId和userName获取用户记录（未使用@param注解指定参数别名，在mapper.xml中使用#{0},#{1}对应参数userId和userName）
     */
    List<User> getUserListByUserIdAndUserName(String userId, String userName);


    /**
     * 根据user对象中的属性获取用户集合（使用@param指定pojo的别名）
     */
    List<User> getUserList(@Param("user") User user);


    /**
     * 根据user对象中的属性获取用户集合
     */
    List<User> getUserList2(User user);


    /**
     * 根据paramMap获取用户集合
     */
    List<User> getUserListByMap(Map<String, String> paramMap);

    /**
     * 根据paramMap获取用户集合（使用@param指定HashMap的别名）
     */
    List<User> getUserListByMap2(@Param("paramMap") Map<String, String> paramMap);


    /**
     * 根据userIds集合获取用户记录集合
     */
    List<User> getUserListByUserIds(List<String> userIds);

    /**
     * 根据userIds集合获取用户记录集合（使用@param指定入参集合别名）
     */
    List<User> getUserListByUserIds2(@Param("userIds") List<String> userIds);


    /**
     * 根据userIds集合获取用户实体映射集合
     */
    @MapKey("userId")
    Map<String, User> getUserIdAndUserMap(List<String> userIds);


    /**
     * 向数据库中插入一条用户记录（userId用uuid生成）
     */
    int insertUser(User user);

    /**
     * 批量保存用户记录
     */
    int batchSave(List<User> userList);


    /**
     * 批量更新 mapper.xml实现为case...when...
     */
    int batchUpdate(List<User> userList);

    /**
     * 批量更新 mapper.xml中实现：根据userList生成多条update语句，批量更新；
     */
    int batchUpdate2(@Param("userList") List<User> userList);
}

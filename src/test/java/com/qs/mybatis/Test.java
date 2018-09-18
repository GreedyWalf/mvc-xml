package com.qs.mybatis;

import com.qs.entity.User;
import com.qs.service.UserService;
import com.qs.service.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元测试：mybatis相关接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/applicationContext.xml"})
public class Test {

    @Resource
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void testGetAllUser() {
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
    }


    @org.junit.Test
    public void testGetUserByUserId() {
        User user = userMapper.getUserByUserId("4d83b53fecbf4a228b3b86fb86bdf4dd");
        System.out.println("-->>user=" + user);
        System.out.println("-->>userName=" + user.getUserName());
    }

    @org.junit.Test
    public void testGetUserListByUserIdAndUserName() {
        List<User> userList = userMapper.getUserListByUserIdAndUserName("4d83b53fecbf4a228b3b86fb86bdf4dd", "admin");
        System.out.println("-->>>size=" + userList.size());
        System.out.println("->>>userName=" + userList.get(0).getUserName());
    }


    @org.junit.Test
    public void testGetUserList() {
        User user = new User();
        user.setUserId("4d83b53fecbf4a228b3b86fb86bdf4dd");
        user.setUserName("admin");
        List<User> userList = userMapper.getUserList2(user);
        System.out.println("-->>>size=" + userList.size());
        System.out.println("->>>userName=" + userList.get(0).getUserName());
    }


    @org.junit.Test
    public void testGetUserListMap() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", "4d83b53fecbf4a228b3b86fb86bdf4dd");
        paramMap.put("userName", "admin");
        //List<User> userList = userMapper.getUserListByMap(paramMap);
        List<User> userList = userMapper.getUserListByMap2(paramMap);
        System.out.println("-->>>size=" + userList.size());
        System.out.println("->>>userName=" + userList.get(0).getUserName());
    }

    @org.junit.Test
    public void testGetUserListByUserIds() {
        List<String> userIds = new ArrayList<String>();
        userIds.add("4d83b53fecbf4a228b3b86fb86bdf4dd");
        userIds.add("f833e59e988e467fb89a561a8cddb37e");
        //List<User> userList = userMapper.getUserListByUserIds(userIds);
        List<User> userList = userMapper.getUserListByUserIds2(userIds);
        System.out.println("-->>>size=" + userList.size());
        System.out.println("->>>userName=" + userList.get(0).getUserName());
    }

    @org.junit.Test
    public void testGetUserIdAndUserMap() {
        List<String> userIds = new ArrayList<String>();
        userIds.add("4d83b53fecbf4a228b3b86fb86bdf4dd");
        userIds.add("f833e59e988e467fb89a561a8cddb37e");
        Map<String, User> userIdAndUserMap = userMapper.getUserIdAndUserMap(userIds);
        System.out.println("-->>>size=" + userIdAndUserMap.size());
        for (Map.Entry<String, User> entry : userIdAndUserMap.entrySet()) {
            System.out.println("--->>>userId=" + entry.getKey() + ",user=" + entry.getValue());
        }
    }


    @org.junit.Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("aaa");
        int cnt = userMapper.insertUser(user);
        System.out.println("--->>>cnt=" + cnt);
    }


    @org.junit.Test
    public void testBatchSaveUser() {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("bbb" + i);
            userList.add(user);
        }

        System.out.println("--->>>>cnt=" + userMapper.batchSave(userList));
    }


    @org.junit.Test
    public void testBatchUpdate() {
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setUserId("6a299094bb3d11e89b1433d594674628");
        user.setUserName("vvv3");

        User user2 = new User();
        user2.setUserId("6a29935abb3d11e89b1433d594674628");
        user2.setUserName("vvv4");

        userList.add(user);
        userList.add(user2);

        //int cnt = userMapper.batchUpdate(userList);
        int cnt = userMapper.batchUpdate2(userList);
        System.out.println("--->>cnt = " + cnt);
    }

}

package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void addUser () {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        Integer rows = userMapper.addUser(user);
        System.out.println(rows);

    }

    @Test
    public void updatePassword () {
        userMapper.updatePassword("123", "admin1", new Date(), 37);
    }

    @Test
    public void findUserByUid () {
        System.out.println(userMapper.findUserByUid(37));
    }
    @Test
    public void updateUserInfoByUid(){
        User user = new User();
        user.setPhone("136512978916");
        user.setEmail("123123411@qq.com");
        user.setUid(37);
        userMapper.updateUserInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateUserAvatarByUid("/upload/qwer.png", "luke", new Date(), 37);
    }
}

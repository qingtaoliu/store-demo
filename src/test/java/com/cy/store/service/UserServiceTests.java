package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void createUser () {
        User user = new User();
        user.setUsername("qingtao");
        user.setPassword("123456");
        Integer result = userService.userRegister(user);
        System.out.println(result);
    }

    @Test
    public void login () {
        User user = userService.login("luke19 liu", "1234567");
        System.out.println(user);

    }

    @Test
    public void changePassword () {
        userService.changePassword(36, "123", "123456");
    }

    @Test
    public void updateUserInfo () {
        User user = new User();
        user.setEmail("qingtao@gmail.com");
        user.setPhone("12306");
        user.setGender(1);
        user.setUid(37);
        userService.changeUserInfo(user);
    }

    @Test
    public void getUserInfoByUid () {
        System.out.println(userService.findUserById(37));
    }

    @Test
    public void updateUserAvatar(){
        userService.changeUserAvatar(37, "/upload/test.ong", "luke");
    }
}

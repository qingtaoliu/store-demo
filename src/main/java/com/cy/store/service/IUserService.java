package com.cy.store.service;

import com.cy.store.entity.User;

import java.util.Date;

public interface IUserService {

    Integer userRegister(User user);

    User login(String username, String password);

    void changePassword(Integer uid, String oldPassword, String newPassword);

    User findUserById(Integer uid);

    void changeUserInfo(User user);

    void changeUserAvatar(Integer uid, String avatar, String username);

    void findUserByUsername(String username);

}

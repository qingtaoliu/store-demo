package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

/*持久层和数据库沟通*/
public interface UserMapper {
    /**
     * 插入用户数据
     * */
    Integer addUser(User user);

    /**
     * 查询用户信息
     * */
    User findByUsername(String username);


    /*
    * 更新密码
    * */
    Integer updatePassword(String password, String modifiedUser, Date modifiedTime, Integer uid);

    /*
    * 根据 uid 查询用户信息
    * */

    User findUserByUid(Integer uid);

    /*
    * 更新用户信息
    * */
    Integer updateUserInfoByUid(User user);

    /*
    * 更新头像
    * */

    Integer updateUserAvatarByUid(String avatar, String modifiedUser, Date modifiedTime, Integer uid);
}

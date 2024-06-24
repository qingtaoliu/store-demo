package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import com.cy.store.units.PasswordEncryptedUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public Integer userRegister(User user) {
        User queryUser = userMapper.findByUsername(user.getUsername());
        if (queryUser != null) {
          throw new UsernameDuplicatedException("用户名已经占用");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String password = PasswordEncryptedUnits.getPasswordByMd5(user.getPassword(), salt);
        Date currentTime = new Date();
        user.setPassword(password);
        user.setCreatedTime(currentTime);
        user.setModifiedTime(currentTime);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setIsDelete(0);
        Integer result = userMapper.addUser(user);
        if (result != 1) {
            throw new InsertException("插入异常");
        }
        return result;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        String salt = user.getSalt();
        String userPwd = user.getPassword();

        if (!PasswordEncryptedUnits.getPasswordByMd5(password, salt).equals(userPwd)) {
            throw new PasswordNotMatchException("用户名密码错误");
        }
        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }

        // 重新构建数据
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());
        //执行到这就是用户正常登录
        return user1;
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) {
       User user = userMapper.findUserByUid(uid);
       if (user == null || user.getIsDelete() == 1) {
           throw new UserNotFoundException("用户不存在");
       }
       String oldPwd = PasswordEncryptedUnits.getPasswordByMd5(oldPassword, user.getSalt());
       System.out.println(oldPwd);
       System.out.println(user.getPassword());
       if (!user.getPassword().equals(oldPwd)){
           throw new PasswordNotMatchException("密码错误");
       }
       if (Objects.equals(oldPassword, newPassword)) {
           throw new PasswordNotMatchException("新老密码不能相同");
       }
       String newPwd = PasswordEncryptedUnits.getPasswordByMd5(newPassword, user.getSalt());
       Integer row =  userMapper.updatePassword(newPwd, user.getUsername(), new Date(), uid);
       if (row != 1) {
           throw new UpdateException("更新异常");
       }
    }



    @Override
    public User findUserById(Integer uid) {
       User user = userMapper.findUserByUid(uid);
       if (user == null) {
           throw new UserNotFoundException("用户不存在");
       }
       return user;
    }

    @Override
    public void changeUserInfo(User user) {
        User result = userMapper.findUserByUid(user.getUid());
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
//        user.setUid(user.getUid());
        user.setModifiedUser(result.getUsername());
        user.setModifiedTime(new Date());
        Integer row = userMapper.updateUserInfoByUid(user);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }

    }

    @Override
    public void changeUserAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findUserByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        Integer row = userMapper.updateUserAvatarByUid(avatar, username, new Date(), uid);
        if (row != 1) {
            throw new UpdateException("更新异常");
        }
    }

    @Override
    public void findUserByUsername(String username) {

    }
}

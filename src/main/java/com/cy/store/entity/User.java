package com.cy.store.entity;

import java.io.Serializable;
import java.util.Objects;

public class User extends BaseEntity implements Serializable {
//     `uid` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
//            `username` varchar(20) NOT NULL COMMENT '用户名',
//            `password` char(32) NOT NULL COMMENT '密码',
//            `salt` char(36) DEFAULT NULL COMMENT '盐值',
//            `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
//            `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
//            `gender` int DEFAULT NULL COMMENT '性别:0-女，1-男',
//            `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
//            `is_delete` int DEFAULT NULL COMMENT '是否删除：0-未删除，1-已删除',
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String phone;
    private String avatar;
    private Integer gender;
    private Integer isDelete;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer is_delete) {
        this.isDelete = is_delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(avatar, user.avatar) && Objects.equals(gender, user.gender) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, email, phone, avatar, gender, isDelete);
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", is_delete=" + isDelete +
                '}';
    }
}

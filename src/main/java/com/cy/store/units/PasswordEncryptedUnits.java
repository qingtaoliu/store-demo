package com.cy.store.units;

import org.springframework.util.DigestUtils;

public class PasswordEncryptedUnits {

    public static String getPasswordByMd5(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}

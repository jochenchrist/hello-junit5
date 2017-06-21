package com.senacor.devcon;

import org.springframework.stereotype.Component;

@Component
public class PasswordPolicy {

    public static final String REGEX = "^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,}$";

    public boolean isStrong(String password) {
        if(password == null) {
            throw  new IllegalArgumentException("password must not be null");
        }
        return password.matches(REGEX);
    }
}

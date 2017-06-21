package com.senacor.devcon;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final PasswordPolicy passwordPolicy;
    private final PasswordEncoder passwordEncoder;

    public UserController(PasswordPolicy passwordPolicy, PasswordEncoder passwordEncoder) {
        this.passwordPolicy = passwordPolicy;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        // check strong password
        passwordPolicy.isStrong(user.getPassword());

        // encode password
        passwordEncoder.encode(user.getPassword());

        // check username in use

        // save to repo

    }
}

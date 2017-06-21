package com.senacor.devcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@ExtendWith(MyExtension.class)
class UserControllerTest {

    UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(new PasswordPolicy(), new BCryptPasswordEncoder(15));
    }

    @Nested
    @DisplayName("an anonymous user")
    class AnonymousUser {

        @Nested
        @DisplayName("when signs up")
        @Tag("slow")
        class WhenSignsUp {

            @BeforeEach
            void setUp() {
                User user = new User();
                user.setUsername("peter");
                user.setPassword("nkcxsDF!");

                userController.createUser(user);
            }

            @Test
            void userIsSavedToRepo() {
            }

            @Test
            void passwordIsEncoded() {
                assumingThat("CI".equals(System.getenv("ENV")),
                        () -> assertTrue(true));
            }



        }

    }


}
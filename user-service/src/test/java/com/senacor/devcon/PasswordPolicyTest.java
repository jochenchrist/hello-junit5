package com.senacor.devcon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPolicyTest {

    PasswordPolicy passwordPolicy;

    @BeforeEach
    void setUp() {
        passwordPolicy = new PasswordPolicy();
    }

    @Test
    void isStrong() {
        assertAll(
                () -> assertTrue(passwordPolicy.isStrong("ABCdef99")),
                () -> assertTrue(passwordPolicy.isStrong("ABCdef99$")),
                () -> assertTrue(passwordPolicy.isStrong("ABCdef$$d")),
                () -> assertTrue(passwordPolicy.isStrong("ABCde8f$$d"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABCdef99", "ABCdef99$"})
    void isStrong(String password) {
        assertThat(passwordPolicy.isStrong(password)).isTrue();
    }


    @Test
    void shouldHandleNullAsIllegalArgumentException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> passwordPolicy.isStrong(null));

        assertEquals("password must not be null", exception.getMessage());
    }

    @Test
    void isWeak() {
        assertAll(
                () -> assertFalse(passwordPolicy.isStrong("dsbhjsdf")),
                () -> assertTrue(passwordPolicy.isStrong("ABCdef9"))
        );
    }

}
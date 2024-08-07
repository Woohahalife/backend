package com.core.backend.user.domain;

public interface PasswordEncryptor {

    String encrypt(String password);
    String decrypt(String password);

}
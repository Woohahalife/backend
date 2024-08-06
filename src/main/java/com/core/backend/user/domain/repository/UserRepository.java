package com.core.backend.user.domain.repository;

import com.core.backend.user.domain.User;


public interface UserRepository {

    User save(final User user);

    boolean existsByEmail(final String email);

    boolean existsByPhoneNumber(final String phoneNumber);
}
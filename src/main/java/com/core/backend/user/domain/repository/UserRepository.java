package com.core.backend.user.domain.repository;

import java.util.Optional;

import com.core.backend.user.domain.User;

public interface UserRepository {

	User save(final User user);

	Optional<User> findByEmail(final String email);

	boolean existsByEmail(final String email);

	boolean existsByPhoneNumber(final String phoneNumber);
}

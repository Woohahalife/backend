package com.core.backend.user.infra.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.backend.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(final String email);

	boolean existsByEmail(final String email);

	boolean existsByPhoneNumber(final String phoneNumber);
}

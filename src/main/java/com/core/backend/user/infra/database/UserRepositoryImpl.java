package com.core.backend.user.infra.database;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final JpaUserRepository jpaUserRepository;

	@Override
	public User save(User user) {
		return jpaUserRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return jpaUserRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return jpaUserRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return jpaUserRepository.existsByPhoneNumber(phoneNumber);
	}
}

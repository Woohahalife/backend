package com.core.backend.user.infra.database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.auth.exception.AuthException;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.user.exception.UserException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final JpaUserRepository repository;

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email)
			.orElseThrow(() -> new AuthException(ErrorCode.BAD_CREDENTIALS));
	}

	@Override
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public boolean existsByPhoneNumber(String phoneNumber) {
		return repository.existsByPhoneNumber(phoneNumber);
	}

	@Override
	public User findById(Long authUser) {
		return repository.findById(authUser)
			.orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND_USER));
	}

	@Override
	public List<User> saveAll(List<User> users) {
		return repository.saveAll(users);
	}
}

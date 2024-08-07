package com.core.backend.user.infra.database;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

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
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
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
	public Optional<User> findById(Long authUser) {
		return repository.findById(authUser);
	}

	@Override
	public List<User> saveAll(List<User> users) {
		return repository.saveAll(users);
	}
}

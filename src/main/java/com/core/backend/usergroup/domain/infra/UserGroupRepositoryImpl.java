package com.core.backend.usergroup.domain.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.usergroup.domain.UserGroup;
import com.core.backend.usergroup.domain.repository.UserGroupRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserGroupRepositoryImpl implements UserGroupRepository {

	private final JpaUserGroupRepository repository;

	@Override
	public List<UserGroup> findAllByUserId(final Long userId) {
		return repository.findAllByUserId(userId);
	}
}

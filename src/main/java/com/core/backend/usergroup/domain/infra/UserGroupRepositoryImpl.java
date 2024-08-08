package com.core.backend.usergroup.domain.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.usergroup.domain.UserGroup;
import com.core.backend.usergroup.domain.repository.UserGroupRepository;
import com.core.backend.usergroup.exception.UserGroupException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserGroupRepositoryImpl implements UserGroupRepository {

	private final JpaUserGroupRepository repository;

	@Override
	public List<UserGroup> findAllByUserId(final Long userId) {
		return repository.findAllByUserId(userId);
	}

	@Override
	public List<UserGroup> findAllByGroupId(Long groupId) {
		return repository.findAllByGroupId(groupId);
	}

	@Override
	public UserGroup findByGroupIdAndUserId(final Long groupId, final Long userId) {
		return repository.findByGroupIdAndUserId(groupId, userId)
			.orElseThrow(() -> new UserGroupException(ErrorCode.INVALID_GROUP_FOR_USER));
	}

	@Override
	public boolean existsByUserIdAndGroupId(final Long userId, final Long groupId) {
		return repository.existsByUserIdAndGroupId(userId, groupId);
	}
}

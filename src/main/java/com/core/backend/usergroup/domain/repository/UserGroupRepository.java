package com.core.backend.usergroup.domain.repository;

import java.util.List;

import com.core.backend.usergroup.domain.UserGroup;

public interface UserGroupRepository {

	List<UserGroup> findAllByUserId(final Long userId);

	List<UserGroup> findAllByGroupId(final Long groupId);

	UserGroup findByGroupIdAndUserId(final Long groupId, final Long userId);

	boolean existsByUserIdAndGroupId(Long userId, Long groupId);
}

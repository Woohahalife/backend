package com.core.backend.usergroup.domain.repository;

import java.util.List;

import com.core.backend.usergroup.domain.UserGroup;

public interface UserGroupRepository {

	List<UserGroup> findAllByUserId(final Long userId);

	UserGroup findByGroupIdAndUserId(Long groupId, Long userId);
}

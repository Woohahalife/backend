package com.core.backend.group.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.group.exception.GroupException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

	private final JpaGroupRepository repository;

	@Override
	public Group save(final Group group) {
		return repository.save(group);
	}

	@Override
	public List<Group> findAll() {
		return repository.findAll();
	}

	@Override
	public Group findById(Long groupId) {
		return repository.findById(groupId)
			.orElseThrow(() -> new GroupException(ErrorCode.NOT_FOUND_GROUP));
	}
}

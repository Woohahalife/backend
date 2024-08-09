package com.core.backend.group.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.common.entity.Status;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.group.application.dto.GroupEntranceServiceRequest;
import com.core.backend.group.application.dto.GroupRegisterServiceRequest;
import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.group.exception.GroupException;
import com.core.backend.group.ui.dto.CreateGroupResponse;
import com.core.backend.group.ui.dto.GroupEntranceResponse;
import com.core.backend.group.ui.dto.GroupInviteResponse;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.usergroup.domain.repository.UserGroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GroupCommandService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;
	private final UserGroupRepository userGroupRepository;

	public CreateGroupResponse createGroup(Long userId, GroupRegisterServiceRequest request) {
		User user = userRepository.findById(userId);

		Group group = Group.of(request.getGroupName());
		group.addUserToGroup(user);

		groupRepository.save(group);

		return CreateGroupResponse.from(group.getId());
	}

	public GroupInviteResponse generateInviteCode(Long userId, Long groupId) {
		Group group = userGroupRepository.findByGroupIdAndUserId(groupId, userId).getGroup();

		return GroupInviteResponse.from(group.generateInviteCode());
	}

	public GroupEntranceResponse groupEntrance(Long userId, GroupEntranceServiceRequest request) {

		User user = userRepository.findById(userId);
		Group group = groupRepository.findByInvitationCodeAndStatus(request.getInvitationCode(), Status.ACTIVE);
		validateEntranceUser(userId, group);

		group.addUserToGroup(user);

		return GroupEntranceResponse.from(group);
	}

	private void validateEntranceUser(Long userId, Group group) {
		if(userGroupRepository.existsByUserIdAndGroupId(userId, group.getId())) {
			throw new GroupException(ErrorCode.ALREADY_ENTRANCE_USER);
		}
	}
}

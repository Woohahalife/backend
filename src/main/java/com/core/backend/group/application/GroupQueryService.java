package com.core.backend.group.application;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.group.domain.Group;
import com.core.backend.group.domain.repository.GroupRepository;
import com.core.backend.group.exception.GroupException;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupSettlementResponse;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementRepository;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.user.domain.User;
import com.core.backend.usergroup.domain.UserGroup;
import com.core.backend.usergroup.domain.repository.UserGroupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupQueryService {

	private final GroupRepository groupRepository;
	private final UserGroupRepository userGroupRepository;
	private final SettlementRepository settlementRepository;

	public List<GroupInfoResponse> getAllGroup(Long userId) {

		List<Group> groupList = userGroupRepository.findAllByUserId(userId).stream()
			.map(UserGroup::getGroup)
			.toList();

		return groupList.stream()
			.map(GroupInfoResponse::from)
			.collect(Collectors.toList());
	}

	public GroupSettlementResponse getGroupSettlements(Long userId, Long groupId) {

		validateGroupMember(userId, groupId);

		List<User> users = userGroupRepository.findAllByGroupId(groupId)
			.stream().map(UserGroup::getUser)
			.toList();

		List<Settlement> settlements = settlementRepository.findByGroupIdAndStatus(groupId, SettlementStatus.SUCCESS);

		return GroupSettlementResponse.of(users, settlements);
	}

	private void validateGroupMember(Long userId, Long groupId) { // TODO : 테스트 필요
		if(!userGroupRepository.existsByUserIdAndGroupId(userId, groupId)) {
			throw new GroupException(ErrorCode.NOT_FOUND_GROUP_MEMBER);
		}
	}
}

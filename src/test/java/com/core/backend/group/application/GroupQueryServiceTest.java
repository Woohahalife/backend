package com.core.backend.group.application;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.core.backend.auth.ui.dto.AuthUser;
import com.core.backend.group.GroupServiceTestFixture;
import com.core.backend.group.domain.Group;
import com.core.backend.group.exception.GroupException;
import com.core.backend.group.ui.dto.GroupInfoResponse;
import com.core.backend.group.ui.dto.GroupMemberResponse;
import com.core.backend.group.ui.dto.GroupSettlementListResponse;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.user.domain.User;

class GroupQueryServiceTest extends GroupServiceTestFixture {

	@Test
	@DisplayName("사용자가 참여한 방목록을 조회할 수 있다.")
	void getAllGroupTest() {
		// given
		AuthUser authUser = new AuthUser(user1.getId());

		Group group1 = setGroupData("testGroup1", user1);
		Group group2 = setGroupData("testGroup2", user1);
		Group group3 = setGroupData("testGroup3", user1);

		groupRepository.saveAll(List.of(group1, group2, group3));

		// when
		List<GroupInfoResponse> responses = groupQueryService.getAllGroup(authUser.getUserId());

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(responses).hasSize(3);
			softAssertions.assertThat(responses)
				.extracting("groupName", "numOfParticipantCount")
				.containsExactly(
					Tuple.tuple("testGroup1", 1),
					Tuple.tuple("testGroup2", 1),
					Tuple.tuple("testGroup3", 1)
				);
		});
	}

	@Test
	@DisplayName("사용자가 참여한 모임방의 기정산 내역을 조회할 수 있다.")
	void getGroupSettlementsTest() {
	    // given
		Group group1 = setGroupData("testGroup1", user1);
		groupRepository.save(group1);

		Settlement settlement1 = createSettlement("settlement1", 30000L, SettlementStatus.SUCCESS, LocalDate.now(),
			LocalDate.now(), "place1", group1);
		Settlement settlement2 = createSettlement("settlement2", 30000L, SettlementStatus.SUCCESS, LocalDate.now(),
			LocalDate.now(), "place1", group1);
		Settlement settlement3 = createSettlement("settlement3", 30000L, SettlementStatus.SUCCESS, LocalDate.now(),
			LocalDate.now(), "place1", group1);

		settlementRepository.saveAll(List.of(settlement1, settlement2, settlement3));

		// when
		List<GroupSettlementListResponse> result = groupQueryService.getGroupSettlements(user1.getId(),
			group1.getId());

		// then
		Assertions.assertThat(result).hasSize(3);
	}

	@Test
	@DisplayName("사용자가 참여한 모임방의 참여 멤버를 조회할 수 있다.")
	void getGroupMembersTest() {
		// given
		Group group1 = setGroupData("testGroup1", user1);
		group1.addUserToGroup(user2);
		group1.addUserToGroup(user3);
		groupRepository.save(group1);

		// when
		List<GroupMemberResponse> result = groupQueryService.getGroupMembers(user1.getId(), group1.getId());

		// then
		Assertions.assertThat(result).hasSize(3);
	}

	@Test
	@DisplayName("모임방에 참여한 사용자 이외의 사용자는 모임방을 조회할 수 없다.")
	void getGroupMembersTestException() {
		// given
		Group group1 = setGroupData("testGroup1", user1);
		group1.addUserToGroup(user2);
		group1.addUserToGroup(user3);
		groupRepository.save(group1);

		User user4 = User.of("string4", "string", "string", "01011114444");
		userRepository.save(user4);
		// when


		// then
		Assertions.assertThatThrownBy(() -> groupQueryService.getGroupMembers(user4.getId(), group1.getId()))
			.isInstanceOf(GroupException.class)
			.extracting("errorCode.message")
			.isEqualTo("해당 모임에 참여한 사용자가 아닙니다.");
	}

	private Settlement createSettlement(
		String settlementName,
		Long totalAmount,
		SettlementStatus status,
		LocalDate groupingAt,
		LocalDate settlementAt,
		String settlementPlace,
		Group group) {
		return Settlement.builder()
			.settlementName(settlementName)
			.totalAmount(totalAmount)
			.settlementStatus(status)
			.groupingAt(groupingAt)
			.settlementAt(settlementAt)
			.settlementPlace(settlementPlace)
			.group(group)
			.build();
	}

	private Group setGroupData(String testGroup1, User user) {
		Group group = Group.of(testGroup1);
		group.addUserToGroup(user);

		return group;
	}
}

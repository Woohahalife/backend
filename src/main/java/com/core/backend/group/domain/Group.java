package com.core.backend.group.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.core.backend.common.entity.BaseEntity;
import com.core.backend.settlement.domain.Settlement;
import com.core.backend.settlement.domain.SettlementStatus;
import com.core.backend.user.domain.User;
import com.core.backend.usergroup.domain.UserGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "`group`")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "group_name", nullable = false)
	private String groupName;

	@Embedded
	@Column(name = "invitation_code")
	private InvitationCode invitationCode;

	@Column(name = "bookmark", nullable = false)
	private boolean bookmark;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserGroup> members = new HashSet<>();

	@Builder
	private Group(String groupName) {
		this.groupName = groupName;
		this.invitationCode = new InvitationCode("00000");
		this.bookmark = false;
	}

	public static Group of(String groupName) {
		return new Group(groupName);
	}

	public void addUserToGroup(User user) {
		UserGroup groupMember = new UserGroup(user, this);
		members.add(groupMember);
	}

	public int getNumberOfGroupMembers() {
		return members.size();
	}

	public String generateInviteCode() {
		this.invitationCode.generateRandomCode();
		return this.invitationCode.getCode();
	}
}

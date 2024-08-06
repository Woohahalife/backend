package com.core.backend.group.domain;

import com.core.backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(name = "invitation_code")
    private String invitationCode;

    @Column(name = "bookmark", nullable = false)
    private boolean bookmark;

    public Group(String groupName, String invitationCode) {
        this.groupName = groupName;
        this.invitationCode = invitationCode;
        this.bookmark = bookmark = false;
    }
}

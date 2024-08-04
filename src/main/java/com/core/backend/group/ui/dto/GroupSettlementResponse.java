package com.core.backend.group.ui.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GroupSettlementResponse {  
  
    private List<GroupMemberResponse> groupMembers;
    private List<GroupSettlementListResponse> settlements;  
  
}
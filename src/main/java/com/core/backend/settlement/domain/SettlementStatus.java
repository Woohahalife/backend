package com.core.backend.settlement.domain;

public enum SettlementStatus {
	OPEN, // 정산 시작
	IN_PROGRESS, // 정산 진행 중
	SUCCESS, // 정산 성공
	FAIL // 정산 실패
}

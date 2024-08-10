package com.core.backend.common.entity;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.common.exception.global.GlobalException;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	public boolean isDeleted() {
		return status == Status.DELETE;
	}

	public void activate() {
		status = Status.ACTIVE;
	}

	public void delete() {
		status = Status.DELETE;
	}

	public void softDelete() {

		if (isDeleted()) {
			throw new GlobalException(ErrorCode.ALREADY_DELETED_DATA);
		}

		delete();
	}
}

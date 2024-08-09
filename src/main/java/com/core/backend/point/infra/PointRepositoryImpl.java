package com.core.backend.point.infra;

import org.springframework.stereotype.Repository;

import com.core.backend.common.exception.ErrorCode;
import com.core.backend.point.domain.Point;
import com.core.backend.point.domain.repository.PointRepository;
import com.core.backend.point.exception.PointException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

	private final JpaPointRepository repository;

	@Override
	public Point findByUserId(final Long userId) {
		return repository.findByUserId(userId)
			.orElseThrow(() -> new PointException(ErrorCode.NOT_FOUND_POINT_INFO));
	}
}

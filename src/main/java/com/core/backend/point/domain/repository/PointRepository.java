package com.core.backend.point.domain.repository;

import com.core.backend.point.domain.Point;

public interface PointRepository {

	Point findByUserId(Long userId);
}

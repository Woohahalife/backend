package com.core.backend.point.infra;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.backend.point.domain.Point;

public interface JpaPointRepository extends JpaRepository<Point, Long> {
	Optional<Point> findByUserId(final Long userId);
}

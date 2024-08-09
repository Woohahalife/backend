package com.core.backend.point.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.point.domain.Point;
import com.core.backend.point.domain.repository.PointRepository;
import com.core.backend.point.ui.dto.PointInfoResponse;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PointQueryService {

	private final UserRepository userRepository;
	private final PointRepository pointRepository;

	public PointInfoResponse getPointInfo(Long userId) {

		User user = userRepository.findById(userId);
		Point point = user.getPoint();
		return PointInfoResponse.of(user.getName(), point);
	}
}

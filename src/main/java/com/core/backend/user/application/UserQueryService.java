package com.core.backend.user.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.user.domain.repository.UserRepository;
import com.core.backend.user.ui.dto.UserMyPageResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {

	private final UserRepository userRepository;

	public UserMyPageResponse getMyPage(Long userId) {
		return UserMyPageResponse.from(userRepository.findById(userId));
	}
}

package com.core.backend.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.auth.exception.AuthException;
import com.core.backend.auth.property.TokenProperties;
import com.core.backend.auth.ui.dto.UserLoginRequest;
import com.core.backend.auth.ui.dto.UserLoginResponse;
import com.core.backend.auth.util.TokenProvider;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.PasswordEncryptor;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthCommandService {

	private final TokenProperties tokenProperties;
	private final TokenProvider tokenProvider;
	private final UserRepository userRepository;
	private final PasswordEncryptor passwordEncryptor;

	public UserLoginResponse localLogin(UserLoginRequest request) {
		User user = verifyEmailAndLoginType(request);
		verifyPassword(request, user, passwordEncryptor);

		String accessToken = tokenProvider.generateAccessToken(tokenProperties, user);

		return UserLoginResponse.of(user, accessToken);
	}

	private void verifyPassword(UserLoginRequest request, User user, PasswordEncryptor passwordEncryptor) {
		if (!user.checkPassword(request.getPassword(), passwordEncryptor)) {
			throw new AuthException(ErrorCode.BAD_CREDENTIALS);
		}
	}

	private User verifyEmailAndLoginType(UserLoginRequest request) {
		return userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new AuthException(ErrorCode.BAD_CREDENTIALS));
	}
}

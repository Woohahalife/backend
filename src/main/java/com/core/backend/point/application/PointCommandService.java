package com.core.backend.point.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.account.domain.Account;
import com.core.backend.account.domain.repository.AccountRepository;
import com.core.backend.account.exception.AccountException;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.point.application.dto.PointConversionServiceRequest;
import com.core.backend.point.domain.Point;
import com.core.backend.point.domain.repository.PointRepository;
import com.core.backend.point.ui.dto.PointConversionResponse;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PointCommandService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final PointRepository pointRepository;

	public PointConversionResponse convertBalanceToPoints(Long userId, PointConversionServiceRequest request) {
		Account account = accountRepository.findByUserIdAndMainAccountTrue(userId);
		validateBalance(request, account);

		account.decreaseBalance(request.getAmount());

		Point point = pointRepository.findByUserId(userId);
		point.increasePoint(request.getAmount());

		return PointConversionResponse.of(account.getBalance(), point.getPoint());
	}

	private void validateBalance(PointConversionServiceRequest request, Account account) {
		if(account.validateSufficientBalance(request.getAmount())) {
			throw new AccountException(ErrorCode.INSUFFICIENT_BALANCE);
		}
	}
}

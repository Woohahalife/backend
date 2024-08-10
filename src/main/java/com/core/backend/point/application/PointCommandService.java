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

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PointCommandService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final PointRepository pointRepository;

	public PointConversionResponse convertBalanceToPoints(Long userId, PointConversionServiceRequest request) {
		List<Account> accounts = accountRepository.findAllByUserIdAndMainAccountTrue(userId);
		if(accounts.isEmpty()) {
			throw new AccountException(ErrorCode.NOT_FOUND_MAIN_ACCOUNT);
		}

		Account account = accounts.get(0);
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

	public PointConversionResponse exchangePointsToBalance(Long userId, PointConversionServiceRequest request) {
		Point point = pointRepository.findByUserId(userId);
		point.decreasePoint(request.getAmount());

		List<Account> accounts = accountRepository.findAllByUserIdAndMainAccountTrue(userId);
		Account account = accounts.get(0);
		validateBalance(request, account);
		account.increaseBalance(request.getAmount());

		return PointConversionResponse.of(account.getBalance(), point.getPoint());
	}
}

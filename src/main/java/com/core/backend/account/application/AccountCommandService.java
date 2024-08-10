package com.core.backend.account.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.backend.account.application.dto.AccountRegisterServiceRequest;
import com.core.backend.account.domain.Account;
import com.core.backend.account.domain.repository.AccountRepository;
import com.core.backend.account.exception.AccountException;
import com.core.backend.account.ui.dto.AccountMarkResponse;
import com.core.backend.common.entity.Status;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.User;
import com.core.backend.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;

	public void registerAccount(Long userId, AccountRegisterServiceRequest request) {

		validateDuplicateAccountNumber(request);

		User user = userRepository.findById(userId);
		Account account = Account.of(request.getBankName(), request.getAccountNumber(), user);

		accountRepository.save(account);
	}

	private void validateDuplicateAccountNumber(AccountRegisterServiceRequest request) {
		if(accountRepository.existByAccountNumber(request.getAccountNumber())) {
			throw new AccountException(ErrorCode.DUPLICATE_ACCOUNT_NUMBER);
		}
	}

	public AccountMarkResponse setAccountMark(Long userId, Long accountId) { // TODO : 대표계좌가 이미 있는 경우 예외처리
		if(accountRepository.existsByUserIdAndMainAccountTrue(userId)) {
			throw new AccountException(ErrorCode.ALREADY_REGISTERED_MAIN_ACCOUNT);
		}

		Account account = accountRepository.findByIdAndUserId(accountId, userId);
		account.setMainAccount(true);
		return AccountMarkResponse.from(account);
	}

	public AccountMarkResponse unsetAccountMark(Long userId, Long accountId) {
		Account account = accountRepository.findByIdAndUserId(accountId, userId);
		account.setMainAccount(false);
		return AccountMarkResponse.from(account);
	}

	public void deleteAccount(Long userId, Long accountId) {
		Account account = accountRepository.findByIdAndUserId(accountId, userId);
		account.softDelete();
	}
}

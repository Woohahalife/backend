package com.core.backend.account.domain;

import org.hibernate.annotations.SQLRestriction;

import com.core.backend.account.exception.AccountException;
import com.core.backend.common.entity.BaseEntity;
import com.core.backend.common.exception.ErrorCode;
import com.core.backend.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_code", nullable = false)
    private BankCode bankCode;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Setter
    @Column(name = "main_account", nullable = false)
    private boolean mainAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account(BankCode bankCode, String accountNumber, User user) {
        this.bankCode = bankCode;
        this.balance = 10000000L; // 일괄적으로 1000만원 보유 (dummy data)
        this.accountNumber = accountNumber;
        this.mainAccount = false;
        this.user = user;
    }

    public static Account of(BankCode bankCode, String accountNumber, User user) {
        return new Account(bankCode, accountNumber, user);
    }

    public boolean validateSufficientBalance(Long amount) {
        return this.balance < amount;
    }

    public void decreaseBalance(Long amount) {
        if (amount == null || amount < 10000) {
            throw new AccountException(ErrorCode.ERROR_AMOUNT_TOO_LOW);
        }

        if(validateSufficientBalance(amount)) {
            throw new AccountException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        this.balance -= amount;
    }

    public void increaseBalance(Long amount) {
        this.balance += amount;
    }
}

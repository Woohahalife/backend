package com.core.backend.account.domain;

import com.core.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_code", nullable = false)
    private BankCode bankCode;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "main_account", nullable = false)
    private boolean mainAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account(BankCode bankCode, Long balance, String accountNumber) {
        this.bankCode = bankCode;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.mainAccount = false;
    }
}

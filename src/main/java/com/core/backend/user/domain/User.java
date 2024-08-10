package com.core.backend.user.domain;

import com.core.backend.common.entity.BaseEntity;
import com.core.backend.point.domain.Point;
import com.core.backend.account.domain.Account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "`user`")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Embedded
	@Column(name = "password", nullable = false)
	private Password password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Point point;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Account> accounts = new ArrayList<>();

	@Builder
	private User(String email,
		String password,
		String name,
		String phoneNumber) {
		this.email = email;
		this.password = toPassword(password);
		this.name = name;
		this.phoneNumber = phoneNumber;

	}

	public static User of(String email, String password, String name, String phoneNumber) {
		User user = new User(email, password, name, phoneNumber);
		Point point = new Point(user);
		user.mappingPoint(point);
		return user;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	private void mappingPoint(Point point) {
		this.point = point;
	}

	public Password toPassword(String password) {
		return new Password(password);
	}

	public User encode(PasswordEncryptor encoder) {
		password = this.password.encodePassword(encoder);
		return this;
	}

	public boolean checkPassword(String password, PasswordEncryptor passwordEncoder) {
		return this.password.decode(passwordEncoder).equals(password);
	}
}

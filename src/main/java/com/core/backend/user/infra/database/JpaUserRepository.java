package com.core.backend.user.infra.database;

import com.core.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(final String email);

    boolean existsByPhoneNumber(final String phoneNumber);
}

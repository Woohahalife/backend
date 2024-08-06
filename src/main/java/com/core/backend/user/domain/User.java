package com.core.backend.user.domain;

import com.core.backend.common.entity.BaseEntity;
import com.core.backend.point.domain.Point;
import com.core.backend.user.application.dto.UserSignUpServiceRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        Point point = new Point(0L, user);
        user.mappingPoint(point);
        return user;
    }

    private void mappingPoint(Point point) {
        this.point = point;
    }

    public Password toPassword(String password) {
        return new Password(password);
    }

    public User encode(String rowPassword, PasswordEncryptor encoder) {
        password = this.password.encodePassword(rowPassword, encoder);
        return this;
    }

    public boolean checkPassword(String password, PasswordEncryptor passwordEncoder) {
        return this.password.decode(passwordEncoder).equals(password);
    }


}

package com.example.myblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CMMN_USER_LOGIN")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CmmnUserLogin {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USE_YN", nullable = false)
    private String useYn;

    @Column(name = "WR_DTM", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDT_DTM")
    private LocalDateTime updatedAt;
}

package com.example.myblog.service;

import com.example.myblog.domain.CmmnUserLogin;
import com.example.myblog.dto.auth.LoginRequest;
import com.example.myblog.dto.auth.LoginResponse;
import com.example.myblog.repository.CmmnUserLoginRepository;
import com.example.myblog.util.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CmmnUserLoginRepository userLoginRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request, HttpSession session) {

        CmmnUserLogin user = userLoginRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if (!Boolean.TRUE.equals(user.getUseYn())) {
            throw new IllegalStateException("비활성화된 계정입니다.");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute(
                SessionConst.LOGIN_USER_ID,
                user.getUserId()
        );

        return new LoginResponse(user.getUserId());
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public LoginResponse me(HttpSession session) {

        String userId = (String)
                session.getAttribute(SessionConst.LOGIN_USER_ID);

        if (userId == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }

        return new LoginResponse(userId);
    }
}

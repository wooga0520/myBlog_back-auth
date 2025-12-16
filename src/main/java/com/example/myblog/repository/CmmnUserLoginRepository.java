package com.example.myblog.repository;

import com.example.myblog.domain.CmmnUserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmmnUserLoginRepository
        extends JpaRepository<CmmnUserLogin, String> {
}

package com.example.hwpparsingserver.service;

import com.example.hwpparsingserver.domain.UserDomain;
import com.example.hwpparsingserver.domain.UserJoinDomain;
import com.example.hwpparsingserver.repository.JpaUserRepository;
import com.example.hwpparsingserver.repository.MyBatisUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private MyBatisUserRepository myBatisUserRepository;

    // JPA를 통한 데이터 처리
    public UserJoinDomain getUserById(String userId) {
        return jpaUserRepository.findByUserId(userId);
    }

    public boolean authUser(UserDomain userDomain) {
        // 데이터베이스에서 사용자 조회
        UserDomain foundUser = myBatisUserRepository.AuthUser(userDomain);
        if (foundUser != null && foundUser.getUserPw().equals(userDomain.getUserPw())) {
            return true; // 인증 성공
        }
        return false; // 인증 실패
    }

    // MyBatis를 통한 데이터 처리
    public UserDomain authenticateUser(UserDomain userDomain) {
        return myBatisUserRepository.AuthUser(userDomain);
    }
}

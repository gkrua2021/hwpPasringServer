package com.example.hwpparsingserver.service;

import com.example.hwpparsingserver.domain.UserJoinDomain;
import com.example.hwpparsingserver.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJoinService {

    @Autowired
    private JpaUserRepository jpaUserRepository;

    // 회원가입 메서드
    public String registerUser(UserJoinDomain userJoinDomain) {
        // 중복 사용자 체크
        if (jpaUserRepository.findByUserId(userJoinDomain.getUserId()) != null) {
            return "이미 존재하는 아이디입니다.";
        }
        if (jpaUserRepository.findByEmail(userJoinDomain.getEmail()) != null) {
            return "이미 사용 중인 이메일입니다.";
        }

        // 사용자 저장
        jpaUserRepository.save(userJoinDomain);
        System.out.println("회원 정보가 저장되었습니다: " + userJoinDomain.toString());
        return "회원가입이 성공적으로 완료되었습니다.";
    }
}

package com.example.hwpparsingserver.service;

import com.example.hwpparsingserver.domain.UserDomain;
import com.example.hwpparsingserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authUser(UserDomain getUserDomain){
        UserDomain repositoryUserDomain = userRepository.AuthUser(getUserDomain);
        if(repositoryUserDomain !=null){
            if(repositoryUserDomain.getUserId().equals(getUserDomain.getUserId())){
                return (repositoryUserDomain.getUserPw().equals(getUserDomain.getUserPw()));
            }
            return false;
        }
        return false;
    }


}

package com.example.hwpparsingserver.repository;

import com.example.hwpparsingserver.domain.UserDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBatisUserRepository {
    UserDomain AuthUser(UserDomain userDomain);
}

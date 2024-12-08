package com.example.hwpparsingserver.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDomain {
    int id;
    String userId;
    String userPw;
    String permission;

}

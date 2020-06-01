package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AllMemberVO {
    private String id;
    private String pw;
    private String roleType;
    private String socialType;

}

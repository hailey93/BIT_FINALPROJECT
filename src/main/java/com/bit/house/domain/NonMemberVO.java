package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NonMemberVO {
    private String orderNo;
    private String nonMemberName;
    private String nonMemberEmail;
    private String nonMemberPw;
    private String nonMemberTel;
    private String nonMemberAddr;
}

package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberTel;
    private String memberAddr;
    private String deliveryAddr1;
    private String deliveryAddr2;
    private String nickName;
    private String memberImg;
    private Date memberSignUpDate;
    private Date memberUpdateDate;
    private int point;
}

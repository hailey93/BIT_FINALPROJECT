package com.bit.house.domain;

import lombok.Data;

import java.sql.Date;

@Data
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

    private String userid;
    private String userName;
    private String userTel;


}

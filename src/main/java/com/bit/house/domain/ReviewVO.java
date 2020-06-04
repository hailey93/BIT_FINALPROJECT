package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewVO {
    private String memberId;
    private String reviewContent;
    private double productRank;
    private String reviewImg1;
    private String reviewImg2;
    private String reviewImg3;
    private String productNo;
    private String orderNo;
}

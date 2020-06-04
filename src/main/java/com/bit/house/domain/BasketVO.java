package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class BasketVO {
    private int basketNo;
    private String memberId;
    private String productNo;
    private String productColor;
    private int sellPrice;
    private String productName;
    private String productMainImg;
    private String modelName;
}

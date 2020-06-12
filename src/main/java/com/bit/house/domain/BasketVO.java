package com.bit.house.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Setter
@Getter
@NoArgsConstructor
@ToString
public class BasketVO {
    private int basketNo;
    private String memberId;
    private String productNo;
    private String productColor;
    private int sellPrice;
    private String productName;
    private String productMainImg;
    private String modelName;

    @Autowired(required = false)
    private ProductVO productVO;

    @Autowired(required = false)
    private ProductOptionVO productOptionVO;

    public BasketVO(int basketNo, String memberId, String productNo, String productColor, int sellPrice,
                    String productName, String productMainImg, String modelName, ProductVO productVO) {
        this.basketNo = basketNo;
        this.memberId = memberId;
        this.productColor = productColor;
        this.productNo = productNo;
        this.sellPrice = sellPrice;
        this.productName = productName;
        this.productMainImg = productMainImg;
        this.modelName = modelName;
        this.productVO = productVO;

    }
}

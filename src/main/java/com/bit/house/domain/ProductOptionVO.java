package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@NoArgsConstructor
public class ProductOptionVO {
    private String productOptionNo;
    private String productNo;
    private String colorCode;
    private int productQty;
    private int productSellingQty;
}

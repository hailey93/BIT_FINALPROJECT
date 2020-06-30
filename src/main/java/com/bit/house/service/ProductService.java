package com.bit.house.service;

import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;

public interface ProductService {

    public void insertProduct(ProductVO productVO);
    public void insertProductOption(ProductOptionVO productOptionVO);
}

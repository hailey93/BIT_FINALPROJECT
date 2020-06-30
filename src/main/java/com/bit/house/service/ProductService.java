package com.bit.house.service;

import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;

import java.util.List;

public interface ProductService {

    public void insertProduct(ProductVO productVO);
    public void insertProductOption(ProductOptionVO productOptionVO);
    List<ProductVO> searchProductListInfo(String sellerId);
}

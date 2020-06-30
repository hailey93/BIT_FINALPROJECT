package com.bit.house.service;

import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.mapper.ProductMapper;
import com.bit.house.mapper.ProductOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductOptionMapper productOptionMapper;


    @Override
    public void insertProduct(ProductVO productVO) {

        productMapper.insertProduct(productVO);
    }

    @Override
    public void insertProductOption(ProductOptionVO productOptionVO) {

        productOptionMapper.insertProductOption(productOptionVO);
    }
}

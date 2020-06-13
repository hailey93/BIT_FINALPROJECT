package com.bit.house.service;

import com.bit.house.domain.ProductVO;

import java.util.Collection;
import java.util.List;

public interface RecommenderService {
    List<String> selectClickProductById();
    /*String selectClickProduct(@SessionAttribute("memberId") String memberId);*/
    String selectClickProduct();
    List<ProductVO> selectProductList(Collection<String> productNos);
    void checkClickHistory();
}

package com.bit.house.mapper;

import com.bit.house.domain.ProductVO;
import com.bit.house.domain.RecommenderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommenderMapper {

    List<RecommenderVO> selectClickProductById();
    /*String selectClickProduct(@SessionAttribute("memberId") String memberId);*/
    String selectClickProduct();
    ProductVO selectProductDetail(String productNo);
}

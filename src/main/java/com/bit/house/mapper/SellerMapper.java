package com.bit.house.mapper;

import com.bit.house.domain.SellerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerMapper {

    public void insertSeller(SellerVO sellerVO);
    public void insertSellerToUser(SellerVO sellerVO);

    SellerVO searchSeller(String id);
    SellerVO searchSellerInfo(String sellerId);
}

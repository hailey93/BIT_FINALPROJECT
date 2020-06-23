package com.bit.house.service;

import com.bit.house.domain.SellerVO;

public interface SellerService {
    public void insertSeller(SellerVO sellerVO);
    public void insertSellerToUser(SellerVO sellerVO);
    SellerVO searchSeller(String id);
}

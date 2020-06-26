package com.bit.house.service;

import com.bit.house.domain.SellerVO;
import com.bit.house.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void insertSeller(SellerVO sellerVO) {


        sellerMapper.insertSeller(sellerVO);
    }

    @Override
    public void insertSellerToUser(SellerVO sellerVO) {

        sellerVO.setSellerPw(passwordEncoder.encode(sellerVO.getSellerPw()));
        sellerMapper.insertSellerToUser(sellerVO);
    }

    @Override
    public SellerVO searchSeller(String id) {

        return sellerMapper.searchSeller(id);
    }

    @Override
    public SellerVO searchSellerInfo(String sellerId) {

        return sellerMapper.searchSellerInfo(sellerId);
    }


}

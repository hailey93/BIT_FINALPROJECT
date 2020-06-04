package com.bit.house.mapper;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.MemberVO;
import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;

import javax.persistence.Entity;
import java.util.List;

public interface AdminMapper {
    public List<ProductVO> getProduct();
    public List<String> getYear();
    public void insertAddr(String userAddr);
    public List<AllMemberVO> getMember();
    public MemberVO getHouseUser(MemberVO houseUser);
    public List<ProductOptionVO> getProductOption();
}

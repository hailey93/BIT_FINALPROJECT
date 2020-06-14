package com.bit.house.mapper;

import com.bit.house.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    public List<ProductVO> getProduct();

    public List<String> getYear();

    public void insertAddr(String userAddr);

    public List<AllMemberVO> getMember();

    public MemberVO getHouseUser(MemberVO houseUser);

    public List<ProductOptionVO> getProductOption();

    public List<ProductVO> getSalesVolume();

    public List<OrderListVO> getTotalPrice();
    public List<OrderListVO> getSpendingPattern();

    public MemberVO getUser();
}

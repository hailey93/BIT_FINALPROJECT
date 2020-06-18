package com.bit.house.mapper;

import com.bit.house.domain.BasketVO;
import com.bit.house.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasketMapper {
    public List<BasketVO> getNonMemberBasketList(List<String> list);

    public List<BasketVO> getArray(String[] hoho2);

    public BasketVO getBasket(String userId);

    public void insertMemberBasket(BasketVO basketVO);



}

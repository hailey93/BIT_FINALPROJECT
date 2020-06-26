package com.bit.house.mapper;

import com.bit.house.domain.MemberVO;
import com.bit.house.domain.OrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyOrderListMapper {

    @Select("select ol.orderDate, p.productName, p.modelName, ol.orderQty, p.sellerName, ol.totalPrice, os.orderType, ol.colorName from orderlist ol join product p on ol.productNo=p.productNo join orderstatus os on ol.ordercode=os.orderCode where memberId=#{memberId}")
    List<OrderListVO> getMyOrderListById(String memberId);

}

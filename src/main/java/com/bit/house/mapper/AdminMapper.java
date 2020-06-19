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
    public List<OrderListVO> getUserPurchaseVolume();
    public List<OrderListVO> getUserDateStat(String date1, String date2);

    public MemberVO getUser();
    public List<OrderListVO> getYearlyPurchaseVolume();
    public List<OrderListVO> getMonthData(String year,String product); // 해당 Year 해당 품목 Month별 데이터 출력
    public List<OrderListVO> getDayData(String year, String month, String product);


    // userInfo
    public List<MemberVO> getUserInfo();
    // orderList
    public List<OrderListVO> getOrderList();

    // 연간 업체 판매량
    public List<OrderListVO> getYearlySellerSalesVolume(String sellerName);
    // 월간
    public List<OrderListVO> getMonthlySellerSalesVolume(String sellerName, String year);
    // 일간
    public List<OrderListVO> getDailySellerSalesVolume(String sellerName, String year, String month);
}

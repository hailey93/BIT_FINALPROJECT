package com.bit.house.mapper;

import com.bit.house.domain.ProductVO;
import com.bit.house.domain.SellerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerAdmMapper {

    public List<SellerVO> sellerManagement() throws Exception;

    public List<ProductVO> sellerProduct(String sellerName) throws Exception;

    public SellerVO sellerStat(String sellerName) throws Exception;

    int productCount(String sellerName) throws Exception;

    public SellerVO sellerDetail(String sellerName) throws Exception;

    public List<ProductVO> allSellerProduct(String sellerName) throws Exception;

    public List<SellerVO> applySeller() throws Exception;

    public SellerVO applySellerDetail(String sellerName) throws Exception;

    public void applyProc(String sellerName) throws Exception;


}

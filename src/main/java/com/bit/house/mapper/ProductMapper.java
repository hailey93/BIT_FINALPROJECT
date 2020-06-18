package com.bit.house.mapper;

import com.bit.house.domain.ColorVO;
import com.bit.house.domain.ProductOptionVO;
import com.bit.house.domain.ProductVO;
import com.bit.house.domain.SellerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("Select * from product")
    List<ProductVO> selectAllProduct();

    @Select("SELECT * FROM product WHERE productname LIKE CONCAT('%',#{searchProduct},'%') OR modelName LIKE CONCAT('%',#{searchProduct},'%') OR sellername LIKE CONCAT('%',#{searchProduct},'%')")
    List<ProductVO> selectProduct(@Param("searchProduct") String searchProduct);

    @Select("select * from product where categoryCode LIKE CONCAT('%',#{categoryCode},'%')")
    List<ProductVO> selectProductByCategory(String categoryCode);

    @Select("SELECT c.colorType FROM productoption po join color c on po.colorCode=c.colorCode where productno=#{productNo}")
    List<String> getProductVOByProductColorCode(String productNo);

    @Select("select * from product where productno=#{productNo}")
    ProductVO getProductVOByProductNo(String productNo);

//    @Select("select s.sellerName, s. from product p join seller s on p.sellerName=s.sellerName where productno =#{productNo}")
//    SellerVO getProductVOBySellerName(String productNo);

}

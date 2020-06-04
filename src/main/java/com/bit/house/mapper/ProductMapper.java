package com.bit.house.mapper;

import com.bit.house.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("Select * from product")
    List<ProductVO> selectAllProduct();

    @Select("SELECT * FROM product WHERE productname LIKE CONCAT('%',#{searchProduct},'%') OR modelName LIKE CONCAT('%',#{searchProduct},'%')")
    List<ProductVO> selectProduct(@Param("searchProduct") String searchProduct);

}

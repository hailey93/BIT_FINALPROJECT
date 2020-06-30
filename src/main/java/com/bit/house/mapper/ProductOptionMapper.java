package com.bit.house.mapper;

import com.bit.house.domain.ProductOptionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductOptionMapper {

    public void insertProductOption(ProductOptionVO productOptionVO);
}

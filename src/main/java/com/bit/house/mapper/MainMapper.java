package com.bit.house.mapper;

import com.bit.house.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MainMapper {
    List<ProductVO> selectMainList();

}

package com.bit.house.mapper;

import com.bit.house.domain.RecommenderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecommenderMapper {
    public List<RecommenderVO> selectClickProductById();
}

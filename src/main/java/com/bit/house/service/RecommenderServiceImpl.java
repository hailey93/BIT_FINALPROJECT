package com.bit.house.service;

import com.bit.house.domain.ProductVO;
import com.bit.house.domain.RecommenderVO;
import com.bit.house.mapper.RecommenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommenderServiceImpl implements RecommenderService {

    @Autowired(required = false)
    RecommenderMapper recommenderMapper;

    @Override
    public List<String> selectClickProductById() {
        List<RecommenderVO> recommenderVO = recommenderMapper.selectClickProductById();

        List<String> clickLists = new ArrayList();
        List<String> clickList = null;

        for (RecommenderVO products : recommenderVO) {
            clickList = new ArrayList<String>();
            for (ProductVO productNo2 : products.getProductVO()) {
                clickList.add(productNo2.getProductNo());
            }
            clickList.toString();
            clickLists.add(clickList.toString());
        }

        return clickLists;
    }
}
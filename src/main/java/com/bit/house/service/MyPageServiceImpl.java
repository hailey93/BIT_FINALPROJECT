package com.bit.house.service;

import com.bit.house.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyPageServiceImpl implements MyPageService{

    @Autowired
    MyPageMapper myPageMapper;

    @Override
    public void deleteNote(List<String> msgNum) throws Exception {
        for(String msgN:msgNum){
            int msgNo=Integer.parseInt(msgN);
            myPageMapper.deleteNote(msgNo);
        }
    }
}

package com.bit.house.mapper;

import com.bit.house.domain.AskBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AskBoardMapper {
    //게시글 목록
    List<AskBoardVO> boardList() throws Exception;

//    //게시글 상세
//    public AskBoardVO askDetail(int askBoardNo) throws Exception;
//
//    //게시글 작성
//    public int insertAsk(AskBoardVO askBoardVO, String editor) throws Exception;
//
//    //게시글 수정

}
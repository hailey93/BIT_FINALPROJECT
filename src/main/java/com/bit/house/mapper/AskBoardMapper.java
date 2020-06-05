package com.bit.house.mapper;

import com.bit.house.domain.AskBoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.bit.house.mapper.AskBoardMapper")
public interface AskBoardMapper {
    //게시글 목록
    public List<AskBoardVO> askBoardList() throws Exception;

    //게시글 상세
    public AskBoardVO askDetail(int askBoardNo) throws Exception;

    //게시글 작성
    public int insertAsk(AskBoardVO askBoardVO, String editor) throws Exception;

    //게시글 수정
    public int askUpdate(AskBoardVO askBoardVO, String editor) throws Exception;

    //게시글 삭제
    public int askDelete(int askBoardNo) throws Exception;

    //답글 작성
    public int askReply(AskBoardVO askBoardVO, String editor) throws Exception;

    //검색




}

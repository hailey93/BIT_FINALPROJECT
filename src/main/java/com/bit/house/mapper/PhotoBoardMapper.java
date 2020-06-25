package com.bit.house.mapper;

import com.bit.house.domain.PhotoBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoBoardMapper {
    //사진게시판 메인
    public List<PhotoBoardVO> communityMain() throws Exception;

    //사진게시판 리스트
    public List<PhotoBoardVO> photoBoardList() throws Exception;

    //사진게시판 업로드
    public void insertPhoto(PhotoBoardVO photoBoardVO) throws Exception;

    //사진게시판 수정
    public String updatePhoto(PhotoBoardVO photoBoardVO) throws Exception;

    //사진게시판 삭제
    public int deletePhoto(int photoBoardNo) throws Exception;

    //사진게시판 상세
    public PhotoBoardVO photoDetail(int photoBoardNo) throws Exception;

    //좋아요, 스크랩은 고민

}

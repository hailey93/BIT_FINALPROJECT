package com.bit.house.mapper;

import com.bit.house.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    //프로필설정
    public MemberVO selectProfile(String memberId) throws Exception;

    //프로필수정
    public void modifyProfile(MemberVO memberVO) throws Exception;

    //팔로워
    public List<FollowVO> follower() throws Exception;

    //팔로잉
    public List<FollowVO> following() throws Exception;

    //팔로우
    public String follow(String memberId) throws Exception;

    //팔로우 취소
    public String cancelFollow(String memberId) throws Exception;

    //내 프로필
    public MemberVO myProfile() throws Exception;

    public List<PhotoBoardVO> profilePhoto() throws Exception;

    public List<ScrapVO> profileScrap() throws Exception;

    //사진 게시글 전체보기
    public List<PhotoBoardVO> allPhoto() throws Exception;

    //스크랩 전체보기
    public List<ScrapVO> allScrap() throws Exception;

    //보낸쪽지함
    public List<MsgVO> sendNote() throws Exception;

    //받은쪽지함
    public List<MsgVO> receiveNote() throws Exception;

    //쪽지 보내기
    public String noteSending(MsgVO msgVO) throws Exception;

    //쪽지 삭제
    public int deleteNote(int msgNo) throws Exception;

}
